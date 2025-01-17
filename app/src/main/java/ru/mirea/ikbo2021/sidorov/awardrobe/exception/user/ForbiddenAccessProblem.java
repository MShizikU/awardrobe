package ru.mirea.ikbo2021.sidorov.awardrobe.exception.user;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ForbiddenAccessProblem extends AbstractThrowableProblem {
    public ForbiddenAccessProblem() {
        super(
                null,
                "Недостаточно прав",
                Status.FORBIDDEN,
                "У Вас недостаточно прав для данной операции");
    }
}
