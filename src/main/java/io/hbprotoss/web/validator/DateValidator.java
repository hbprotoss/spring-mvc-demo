package io.hbprotoss.web.validator;

import io.hbprotoss.web.validator.annotation.CheckDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by hbprotoss on 6/15/16.
 */
public class DateValidator implements ConstraintValidator<CheckDate, String> {
    private SimpleDateFormat formatter;
    private String pattern;
    private boolean required;

    @Override
    public void initialize(CheckDate constraintAnnotation) {
        pattern = constraintAnnotation.pattern();
        required = constraintAnnotation.required();
        formatter = new SimpleDateFormat(pattern);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required && pattern == null) {
            return false;
        }
        if (value.isEmpty()) {
            return true;
        }
        try {
            formatter.parse(value);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
