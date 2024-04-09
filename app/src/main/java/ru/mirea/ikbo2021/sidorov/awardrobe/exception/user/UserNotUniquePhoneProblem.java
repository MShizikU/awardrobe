package ru.mirea.ikbo2021.sidorov.awardrobe.exception.user;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class UserNotUniquePhoneProblem extends AbstractThrowableProblem {
    public UserNotUniquePhoneProblem(String value) {
        super(
                null,
                "Пользователь с таким телефоном уже зарегистрирован",
                Status.CONFLICT,
                String.format("Пользователь с номером телефона '%s' уже зарегистрирован", value));
    }
}
