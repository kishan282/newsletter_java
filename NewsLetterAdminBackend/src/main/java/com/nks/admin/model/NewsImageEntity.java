package com.nks.admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;*/

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "images")
public class NewsImageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long imageid;

	@Column(name = "image")
	@Lob
	private final byte[] images;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "article_id")
	@JsonIgnore
	private final NewsSubmitEntity newsSubmitEntity;
	/*
	 * protected NewsImageEntity() { this.images = null; this.newsSubmitEntity =
	 * null; }
	 */

	private NewsImageEntity(byte[] images, NewsSubmitEntity newsSubmitEntity) {
		this.images = images;
		this.newsSubmitEntity = newsSubmitEntity;
	}

	public long getImageid() {
		return imageid;
	}

	/*
	 * public void setImageid(long imageid) { this.imageid = imageid; }
	 */

	public byte[] getImages() {
		return images.clone();
	}

	public static class ImageBuilder{
		private byte[] images;
		private final NewsSubmitEntity newsSubmitEntity;
		
		private ImageBuilder(NewsSubmitEntity news) {
			this.newsSubmitEntity = news;
		}
		
		public ImageBuilder images(byte[] images) {
			this.images = images.clone();
			return this;
		}
		
		/*
		 * public ImageBuilder addnewsSubmit(NewsSubmitEntity news) {
		 * this.newsSubmitEntity = news; return this; }
		 */
		
		public NewsImageEntity build() {
			return new NewsImageEntity(images, newsSubmitEntity);
		}
	}
	
	public static ImageBuilder builder(NewsSubmitEntity news) {
		return new ImageBuilder(news);
	}
	
	/*
	 * public void setImages(byte[] images) { this.images = images; }
	 */

	/*
	 * public void setNewsSubmitEntity(NewsSubmitEntity newsSubmitEntity) {
	 * this.newsSubmitEntity = newsSubmitEntity; }
	 */

}
