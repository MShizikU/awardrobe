package ru.mirea.ikbo2021.sidorov.awardrobe.validation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.mirea.ikbo2021.sidorov.awardrobe.validation.constraints.StatusValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StatusValidator.class})
public @interface ValidStatus {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Позволяет указать, что поле может быть null
     */
    boolean nullable() default false;
}
