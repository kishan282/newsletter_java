package com.nks.admin.dto.response;

public final class AddAdminOrEditorResponse {

	private final String userId;
	private final String status;
	
	public String getUserId() {
		return userId;
	}
	public String getStatus() {
		return status;
	}
	
	public AddAdminOrEditorResponse(String userId, String status) {
		this.userId = userId;
		this.status = status;
	}
	
}
