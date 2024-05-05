package ru.mirea.ikbo2021.sidorov.awardrobe.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.config.security.SuperUserConfig;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.user.UserNotUniqueEmailProblem;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.user.UserNotUniquePhoneProblem;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.user.UserNotUniqueUsernameProblem;
import ru.mirea.ikbo2021.sidorov.awardrobe.model.User;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final SuperUserConfig superUserConfig;

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
}
