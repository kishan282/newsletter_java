package com.nks.admin.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "news_entity")
public class NewsSubmitEntity implements Cloneable {

	@Id
	@Column(name = "article_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long articleID;

	@Column(name = "headline", nullable = false)
	private final String headline;

	@Column(name = "categories")
	private final String categories;

	@Column(name = "states_or_ut")
	private final String states_or_ut;

	@Column(name = "story")
	@Lob
	private final String story;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "newsSubmitEntity", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<NewsImageEntity> listOfImageEntities;

	protected NewsSubmitEntity() {
		this.headline = "";
		this.categories = "";
		this.states_or_ut = "";
		this.story = "";
	}

	public NewsSubmitEntity(String headline, String categories, String states_or_ut, String story) {
		this.headline = headline;
		this.categories = categories;
		this.states_or_ut = states_or_ut;
		this.story = story;
		this.listOfImageEntities = new ArrayList<>();
	}

	public void addNewsImageEntity(NewsImageEntity newsImage) {
		listOfImageEntities.add(newsImage);
	}

	public NewsSubmitEntity withAddedNewsImageEntity(NewsImageEntity newsImage) {
		NewsSubmitEntity newNewsEntity = new NewsSubmitEntity(this.headline, this.categories, this.states_or_ut,
				this.story);
		newNewsEntity.listOfImageEntities = new ArrayList<>(this.listOfImageEntities);
		newNewsEntity.listOfImageEntities.add(newsImage);
		return newNewsEntity;
	}

	public List<NewsImageEntity> getListOfImageEntities() {
		return Collections.unmodifiableList(listOfImageEntities);
	}

	/*
	 * public void setListOfImageEntities(List<NewsImageEntity> listOfImageEntities)
	 * { this.listOfImageEntities = listOfImageEntities;
	 * listOfImageEntities.forEach(listOfImageEntitie -> {
	 * listOfImageEntitie.setNewsSubmitEntity(this); }); }
	 */
	
	public Long getArticleID() {
		return articleID;
	}

	/*
	 * public void setArticleID(String articleID) { this.articleID = articleID; }
	 */

	public String getHeadline() {
		return headline;
	}

	/*
	 * public void setHeadline(String headline) { this.headline = headline; }
	 */

	public String getCategories() {
		return categories;
	}

	/*
	 * public void setCategories(String categories) { this.categories = categories;
	 * }
	 */

	public String getStates_or_ut() {
		return states_or_ut;
	}

	/*
	 * public void setStates_or_ut(String states_or_ut) { this.states_or_ut =
	 * states_or_ut; }
	 */

	public String getStory() {
		return story;
	}

	@Override
	protected NewsSubmitEntity clone() throws CloneNotSupportedException {
		return (NewsSubmitEntity) super.clone();
	}

	/*
	 * public void setStory(String story) { this.story = story; }
	 */

}
