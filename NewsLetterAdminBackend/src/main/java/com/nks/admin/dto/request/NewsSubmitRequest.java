package com.nks.admin.dto.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class NewsSubmitRequest {

	@NotBlank(message = "'Headline is a mandatory field'")
	private final String headline;
	@NotBlank(message = "'Categories is a mandatory field'")
	private final String categories;
	private final String states_or_ut;
	@NotBlank(message = "'Store is a mandatory field'")
	private final String story;

	@JsonCreator
	public NewsSubmitRequest(@JsonProperty("headline") String headline, @JsonProperty("categories") String categories,
			@JsonProperty("states_or_ut") String states_or_ut, @JsonProperty("story") String story) {
		this.headline = headline;
		this.categories = categories;
		this.states_or_ut = states_or_ut;
		this.story = story;
	}

	public String getHeadline() {
		return headline;
	}

	public String getCategories() {
		return categories;
	}

	public String getStates_or_ut() {
		return states_or_ut;
	}

	public String getStory() {
		return story;
	}

}
