package com.github.colax.util.validation;


import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class Validations {
	private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
	private static final Validator VALIDATOR = VALIDATOR_FACTORY.getValidator();

	public static void validate(Object object) {
		Set<ConstraintViolation<Object>> violations = VALIDATOR.validate(object);
		if (CollectionUtils.isNotEmpty(violations)) {
			String message = violations.iterator().next().getMessage();
			throw new IllegalArgumentException(message);
		}
	}
}