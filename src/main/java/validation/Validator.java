package validation;

import exception.GeneralValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;
import java.util.stream.Collectors;

public class Validator {

    private static javax.validation.Validator validator;

    public static <T> void validate(T object) {
        if(validator == null) {
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }

        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if(!violations.isEmpty()) {
            throw new GeneralValidationException("Validation error")
                    .setValidationErrors(
                            violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList())
                    ).setClazz(object.getClass());
        }
    }

}
