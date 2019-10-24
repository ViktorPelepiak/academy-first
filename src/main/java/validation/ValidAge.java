package validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = AgeConstraintValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidAge {
    int DEFAULT_AGE = 18;

    int min() default DEFAULT_AGE;

    String message() default "Age must be greater than " + DEFAULT_AGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
