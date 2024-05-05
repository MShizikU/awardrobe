package ru.mirea.ikbo2021.sidorov.awardrobe.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.config.security.SuperUserConfig;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.Status;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.user.UserNotFound;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.user.UserNotUniqueEmailProblem;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.user.UserNotUniqueUsernameProblem;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.User;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.UserRepository;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.UserRoleRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final SuperUserConfig superUserConfig;

    /**
     * Получение пользователя по username
     *
     * @return пользователь
     */
    public User getByUsername(String username) {
        UserRepository userRepository;
        return repository.findByUsername(username).orElseThrow(() -> new UserNotFound(username));
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
            u.genHash();
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
}
