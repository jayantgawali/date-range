package com.wipro.date.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateRangeValidator.class)
public @interface ValidDateRange {
    String message() default "dateRange.error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
