package com.nks.admin.validation;

import java.util.HashMap;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.nks.admin.customexceptionhandler.JavaJsonMapperException;

public final class JSONParameterValidator {

	@SuppressWarnings("unused")
	private final Object dtoClass;

	public JSONParameterValidator(Object dtoClass) {
		this.dtoClass = dtoClass;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Object>> violations = validator.validate(dtoClass);

		HashMap<String, String> errorDetails = new HashMap<>();
		
		if (!violations.isEmpty()) {

			for (ConstraintViolation<Object> violation : violations) {
				errorDetails.put(violation.getPropertyPath().toString(), violation.getMessage().toString());
			}

			throw new JavaJsonMapperException(errorDetails);
		}
		
		factory.close();
	}

}
