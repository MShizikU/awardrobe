package ru.mirea.ikbo2021.sidorov.awardrobe.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.config.security.SuperUserConfig;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Company;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.UserRole;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.Status;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.general.EntityNotFound;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.user.ForbiddenAccessProblem;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.user.InvalidUserPasswordProblem;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.user.UserNotUniqueEmailProblem;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.user.UserNotUniqueUsernameProblem;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.User;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.CompanyRepository;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.UserRepository;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final SuperUserConfig superUserConfig;
    private final RoleService roleService;
    private final CompanyRepository companyRepository;

    /**
     * Получение пользователя по username
     *
     * @return пользователь
     */
    public User getByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new EntityNotFound("Пользователь", "username", username));
    }

    /**
     * Получение пользователя по ID
     *
     * @return пользователь
     */
    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    /**
     * Строгое получение пользователя по ID
     *
     * @return пользователь
     */
    public User getByIdStrict(Long id) {
        var user = repository.findById(id);
        if (user.isEmpty()) throw new EntityNotFound("user", "id", id.toString());
        return user.get();
    }


    /**
     * Получение текущего пользователя из токена
     *
     * @return текущий пользователь
     */
    public User getCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(name);
    }

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public User save(User user) {
        return repository.save(user);
    }

    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new UserNotUniqueUsernameProblem(user.getUsername());
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new UserNotUniqueEmailProblem(user.getEmail());
        }

        return save(user);
    }

    /**
     * Получение по фильтру
     *
     * @param filter фильтр
     * @return Найденные пользователи
     */
    public List<User> getByFilter(UserFilter filter) {

        return repository.findByFilter(
                filter.id(),
                filter.role_id(),
                filter.username(),
                filter.email(),
                filter.status()
        );
    }

    public User changeUser(Long userId, UpdateUserRequest request){
        User currentUser = getCurrentUser();
        User user = getByIdStrict(userId);
        UserRole role = roleService.getByIdStrict(request.role_id());

        if (!hasAccessToUser(currentUser, user)) {
            throw new ForbiddenAccessProblem();
        }

        user.setRole(role);
        user.setStatus(request.status());
        user.setEmail(request.email());
        return save(user);
    }

    /**
     * Изменить роль пользователя
     *
     * @param request Запрос на изменение роли
     */
    public User changeRole(UpdateUserRoleRequest request) {
        User currentUser = getCurrentUser();
        User user = getByIdStrict(request.id());
        UserRole role = roleService.getByRoleName(request.role());

        if (!hasAccessToUser(currentUser, user)) {
            throw new ForbiddenAccessProblem();
        }

        user.setRole(role);
        return save(user);
    }

    /**
     * Установка компании пользователю
     * @param request - запрос на установку
     * @return сохраненный пользователь
     */
    public User changeCompany(UpdateUserCompanyRequest request) {
        User currentUser = getCurrentUser();
        User user = getByIdStrict(request.id());
        var companyOpt = companyRepository.findById(request.company_id());
        if (companyOpt.isEmpty()) throw new EntityNotFound("company", "id", request.company_id().toString());
        var company = companyOpt.get();


        if (!hasAccessToUser(currentUser, user)) {
            throw new ForbiddenAccessProblem();
        }

        user.setCompany(company);
        return save(user);
    }


    /**
     * Изменить пароль пользователя
     *
     * @param request Запрос на изменение пароля
     */
    public User changePassword(UpdateUserPasswordRequest request) {
        User currentUser = getCurrentUser();

        if (!passwordEncoder.matches(request.oldPassword(), currentUser.getPassword())) {
            throw new InvalidUserPasswordProblem();
        }

        currentUser.setPassword(passwordEncoder.encode(request.newPassword()));
        return save(currentUser);
    }

    /**
     * Создание суперпользователя, если его нет
     */
    @PostConstruct
    public void initSuperUser() {
        if (!superUserConfig.isSuperuserEnabled()) {
            return;
        }
        var superuserId = superUserConfig.getSuperuserId();
        var user = repository.findById(superuserId);

        if (user.isEmpty()) {
            User u = new User();
            u.setId(superuserId);
            u.setIsDisposable(false);
            u.setStatus(Status.ACTIVE.getStatus());
            u.setUsername("superuser");
            u.setEmail("superuser" + "@test.ru");
            u.setPassword(passwordEncoder.encode(superUserConfig.getSuperuserDefaultPassword()));
            u.setRole(roleRepository.findByName("ADMIN").get());
            repository.save(u);
            log.info("Создан суперпользователь с именем пользователя superuser и паролем superuser");
        } else {
            if (!user.get().isAdmin()) {
                user.get().setRole(roleRepository.findByName("ADMIN").get());
                repository.save(user.get());
                log.info("Пользователь с ID {} теперь является суперпользователем", superuserId);
            }
        }
    }

    public boolean hasAccessToUser(User currentUser, User user) {
        /*
            Права доступа:
            Суперпользователь - Админ - да
            Суперпользователь - Пользователь - да

            Админ - Админ - нет
            Админ - Пользователь - да

            Управлять собой - нет
         */
        if (currentUser.getId().equals(user.getId())) {
            return false;
        }

        if (superUserConfig.isSuperuser(currentUser.getId())) {
            return true;
        }

        // Проверка никогда не должна сработать, но предосторожность не помешает
        if (superUserConfig.isSuperuser(user.getId())) {
            return false;
        }

        if (currentUser.isAdmin()) {
            return !user.getRole().getName().equals("ADMIN");
        }

        return false;
    }

}
