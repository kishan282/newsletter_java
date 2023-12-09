package com.nks.admin.dto.response;

public final class NewsSubmitResponse {
	
	private final Long articleId;
	private final String status;
	
	public Long getArticleId() {
		return articleId;
	}
	public String getStatus() {
		return status;
	}
	
	public NewsSubmitResponse(Long id, String status) {
		this.articleId = id;
		this.status = status;
	}

}
