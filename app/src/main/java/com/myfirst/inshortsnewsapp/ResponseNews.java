package com.myfirst.inshortsnewsapp;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ResponseNews implements Serializable {

	@SerializedName("category")
	private String category;

	@SerializedName("data")
	private List<DataNews> data;

	@SerializedName("success")
	private boolean success;

	public String getCategory(){
		return category;
	}

	public List<DataNews> getData(){
		return data;
	}

	public boolean isSuccess(){
		return success;
	}
}