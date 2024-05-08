package ru.mirea.ikbo2021.sidorov.awardrobe.exception.user;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class UserNotFound extends AbstractThrowableProblem {
    public UserNotFound(String value) {
        super(
                null,
                "Пользователь не найден",
                Status.NOT_FOUND,
                String.format("Пользователь с именем %s не найден", value));
    }
}
