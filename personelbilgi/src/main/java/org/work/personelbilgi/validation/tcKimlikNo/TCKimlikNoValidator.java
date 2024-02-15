package org.work.personelbilgi.validation.tcKimlikNo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TCKimlikNoValidator implements ConstraintValidator<ValidTCKimlikNo, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || !value.matches("[1-9][0-9]{10}")) {
            return false;
        }
        int lastDigit = Character.getNumericValue(value.charAt(value.length() - 1));
        return lastDigit % 2 == 0;
    }
}
