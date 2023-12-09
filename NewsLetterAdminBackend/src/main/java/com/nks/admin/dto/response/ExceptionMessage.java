package com.nks.admin.dto.response;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ExceptionMessage {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private final LocalDateTime timestamp = LocalDateTime.now();
	@JsonProperty
	private final HashMap<String, String> errorMessage;
	@JsonProperty
	private final String path;

	@SuppressWarnings("unchecked")
	public ExceptionMessage(HashMap<String, String> errorMessage, String path) {
		this.errorMessage = (HashMap<String, String>) errorMessage.clone();
		this.path = path;
	}
}
