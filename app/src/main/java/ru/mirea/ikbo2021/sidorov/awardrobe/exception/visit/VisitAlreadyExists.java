package ru.mirea.ikbo2021.sidorov.awardrobe.exception.visit;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class VisitAlreadyExists extends AbstractThrowableProblem {
    public VisitAlreadyExists(Long user_id, Long cell_id) {
        super(
                null,
                "Открытый визит у пользователя уже есть",
                Status.CONFLICT,
                String.format("Открытый визит для такого пользователя с ID %s уже существует", user_id.toString()));
    }
}
