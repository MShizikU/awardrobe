package ru.mirea.ikbo2021.sidorov.awardrobe.exception.general;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class EntityNotFound extends AbstractThrowableProblem {
    public EntityNotFound(String entityName, String fieldName, String fieldValue) {
        super(
                null,
                String.format("Сущность %s не найдена", entityName),
                Status.NOT_FOUND,
                String.format("Сущность %s с параметром %s равным %s не найдена", entityName, fieldName, fieldValue ));
    }
}
