package ru.mirea.ikbo2021.sidorov.awardrobe.exception.company;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class CompanyFieldNotValid extends AbstractThrowableProblem {
    public CompanyFieldNotValid(String value) {
        super(
                null,
                "Поле не правильно заполнено",
                Status.NOT_FOUND,
                String.format("Данные в поле %s не корректны", value));
    }
}

