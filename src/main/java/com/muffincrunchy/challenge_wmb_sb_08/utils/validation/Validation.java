package com.muffincrunchy.challenge_wmb_sb_08.utils.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.xml.validation.Validator;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Validation {

    private final Validator validator;
    public void validate(Object o) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(o);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
