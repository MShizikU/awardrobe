package ru.mirea.ikbo2021.sidorov.awardrobe.exception.general;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class EntityFieldNotValid extends AbstractThrowableProblem {
    public EntityFieldNotValid(String entityName, String fieldName, String fieldValue) {
        super(
                null,
                String.format("Параметр сущности %s не корректен", entityName),
                Status.NOT_FOUND,
                String.format("Параметр %s сущности %s не может быть равен %s", fieldName, entityName, fieldValue ));
    }
}
