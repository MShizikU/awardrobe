package ru.mirea.ikbo2021.sidorov.awardrobe.exception.company;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class CompanyNotFoundID extends AbstractThrowableProblem {
    public CompanyNotFoundID(Long value) {
        super(
                null,
                "Компания не найдена",
                Status.NOT_FOUND,
                String.format("Компания с ID %s не найдена", value));
    }
}