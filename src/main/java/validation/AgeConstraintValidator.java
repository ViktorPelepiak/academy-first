package validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AgeConstraintValidator implements ConstraintValidator<ValidAge, LocalDate> {
    private ValidAge a;

    @Override
    public void initialize(final ValidAge arg0) {
        a = arg0;
    }

    @Override
    public boolean isValid(final LocalDate dob, final ConstraintValidatorContext context) {
        return Period.between(dob, LocalDate.now()).getYears() >= a.min();
    }

}
