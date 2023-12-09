package com.nks.admin.customexceptionhandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nks.admin.dto.response.ExceptionMessage;

@RestControllerAdvice
public class ControllerExceptionAdvisor {

	@ExceptionHandler(JavaJsonMapperException.class)
	public ResponseEntity<ExceptionMessage> jsonMappingException(JavaJsonMapperException jx,
			HttpServletRequest request) {
		ExceptionMessage errorBody = new ExceptionMessage(jx.getErrorDetails(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
	}

}