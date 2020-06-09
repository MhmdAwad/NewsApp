package com.mhmdawad.newsapp.models.saved;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.mhmdawad.newsapp.models.Source;
import com.mhmdawad.newsapp.models.converters.SourceConverter;
import com.mhmdawad.newsapp.utils.Constants;

@Entity(tableName = Constants.SAVED_TABLE_NAME)
@TypeConverters(SourceConverter.class)
public class SavedArticle implements Parcelable {

	public SavedArticle() {
	}

	@PrimaryKey(autoGenerate = true)
	private int uid;
	@ColumnInfo(name = "publishedAt")
	private String publishedAt;
	@ColumnInfo(name = "author")
	private String author;
	@ColumnInfo(name = "urlToImage")
	private String urlToImage;
	@ColumnInfo(name = "description")
	private String description;
	@ColumnInfo(name = "source")
	private Source source;
	@ColumnInfo(name = "title")
	private String title;
	@ColumnInfo(name = "url")
	private String url;
	@ColumnInfo(name = "content")
	private String content;

	public SavedArticle(String publishedAt, String author, String urlToImage, String description, Source source, String title, String url, String content) {
		this.publishedAt = publishedAt;
		this.author = author;
		this.urlToImage = urlToImage;
		this.description = description;
		this.source = source;
		this.title = title;
		this.url = url;
		this.content = content;
	}

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

	protected SavedArticle(Parcel in) {
		uid = in.readInt();
		publishedAt = in.readString();
		author = in.readString();
		urlToImage = in.readString();
		description = in.readString();
		title = in.readString();
		url = in.readString();
		content = in.readString();
	}

	public static final Creator<SavedArticle> CREATOR = new Creator<SavedArticle>() {
		@Override
		public SavedArticle createFromParcel(Parcel in) {
			return new SavedArticle(in);
		}

		@Override
		public SavedArticle[] newArray(int size) {
			return new SavedArticle[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(uid);
		dest.writeString(publishedAt);
		dest.writeString(author);
		dest.writeString(urlToImage);
		dest.writeString(description);
		dest.writeString(title);
		dest.writeString(url);
		dest.writeString(content);
	}



}