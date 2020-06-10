package com.mhmdawad.newsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.mhmdawad.newsapp.models.converters.SourceConverter;
import com.mhmdawad.newsapp.utils.Constants;

@Entity(tableName = Constants.ARTICLES_TABLE_NAME)
@TypeConverters(SourceConverter.class)
public class ArticlesItem implements Parcelable {

	public ArticlesItem() {
	}

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


	public static final DiffUtil.ItemCallback<ArticlesItem> CALLBACK = new DiffUtil.ItemCallback<ArticlesItem>() {
		@Override
		public boolean areItemsTheSame(@NonNull ArticlesItem articlesItem, @NonNull ArticlesItem articlesItem2) {
			return articlesItem.getUid() == articlesItem2.getUid();
		}

		@Override
		public boolean areContentsTheSame(@NonNull ArticlesItem articlesItem, @NonNull ArticlesItem articlesItem2) {
			return true;
		}
	};


	protected ArticlesItem(Parcel in) {
		uid = in.readInt();
		publishedAt = in.readString();
		author = in.readString();
		urlToImage = in.readString();
		description = in.readString();
		title = in.readString();
		url = in.readString();
		content = in.readString();
	}

	public static final Creator<ArticlesItem> CREATOR = new Creator<ArticlesItem>() {
		@Override
		public ArticlesItem createFromParcel(Parcel in) {
			return new ArticlesItem(in);
		}

		@Override
		public ArticlesItem[] newArray(int size) {
			return new ArticlesItem[size];
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