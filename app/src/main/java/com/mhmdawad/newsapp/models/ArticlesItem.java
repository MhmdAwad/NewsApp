package com.mhmdawad.newsapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.mhmdawad.newsapp.utils.Constants;
import com.mhmdawad.newsapp.utils.SourceConverter;

@Entity(tableName = Constants.TABLE_NAME)
@TypeConverters(SourceConverter.class)
public class ArticlesItem{

	@PrimaryKey(autoGenerate = true)
	private int uid;
	@ColumnInfo(name = "publishedAt")
	@SerializedName("publishedAt")
	private String publishedAt;
	@ColumnInfo(name = "author")
	@SerializedName("author")
	private String author;
	@ColumnInfo(name = "urlToImage")
	@SerializedName("urlToImage")
	private String urlToImage;
	@ColumnInfo(name = "description")
	@SerializedName("description")
	private String description;
	@ColumnInfo(name = "source")
	@SerializedName("source")
	private Source source;
	@ColumnInfo(name = "title")
	@SerializedName("title")
	private String title;
	@ColumnInfo(name = "url")
	@SerializedName("url")
	private String url;
	@ColumnInfo(name = "content")
	@SerializedName("content")
	private String content;

	public String getPublishedAt(){
		return publishedAt;
	}

	public String getAuthor(){
		return author;
	}

	public String getUrlToImage(){
		return urlToImage;
	}

	public String getDescription(){
		return description;
	}

	public Source getSource(){
		return source;
	}

	public String getTitle(){
		return title;
	}

	public String getUrl(){
		return url;
	}

	public String getContent(){
		return content;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
}