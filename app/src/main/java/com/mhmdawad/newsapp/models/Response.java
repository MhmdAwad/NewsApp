package com.mhmdawad.newsapp.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("totalResults")
	private int totalResults;

	@SerializedName("articles")
	private List<ArticlesItem> articles;

	@SerializedName("status")
	private String status;

	public int getTotalResults(){
		return totalResults;
	}

	public List<ArticlesItem> getArticles(){
		return articles;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public void setArticles(List<ArticlesItem> articles) {
		this.articles = articles;
	}
}