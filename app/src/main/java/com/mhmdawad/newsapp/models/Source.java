package com.mhmdawad.newsapp.models;

import com.google.gson.annotations.SerializedName;

public class Source{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private Object id;

	public String getName(){
		return name;
	}

	public Object getId(){
		return id;
	}
}