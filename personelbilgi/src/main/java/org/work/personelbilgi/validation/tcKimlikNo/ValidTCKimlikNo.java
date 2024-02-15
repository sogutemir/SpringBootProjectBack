package org.work.personelbilgi.validation.tcKimlikNo;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = TCKimlikNoValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTCKimlikNo {

    String message() default "Geçersiz T.C. Kimlik Numarası";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
