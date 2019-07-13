package com.wipro.date.validation;

import com.wipro.date.domain.DateForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, DateForm> {
    @Override
    public void initialize(ValidDateRange constraintAnnotation) {

    }

    @Override
    public boolean isValid(DateForm dateForm, ConstraintValidatorContext constraintValidatorContext) {
        if(dateForm.getEndDate() == null || dateForm.getStartDate() == null) {
            return true;
        }
        return dateForm.getEndDate().after(dateForm.getStartDate());
    }
}
