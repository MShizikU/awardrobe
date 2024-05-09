package ru.mirea.ikbo2021.sidorov.awardrobe.validation.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.Status;
import ru.mirea.ikbo2021.sidorov.awardrobe.validation.validator.ValidStatus;

import static java.util.Objects.isNull;

public class StatusValidator implements ConstraintValidator<ValidStatus, String> {

    private boolean nullable;

    @Override
    public void initialize(ValidStatus constraintAnnotation) {
        nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (isNull(s)) {
            return nullable;
        }
        try {
            Status status = Status.valueOf(s);
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
