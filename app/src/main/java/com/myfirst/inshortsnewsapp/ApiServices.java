package com.myfirst.inshortsnewsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("news")
    Call<ResponseNews> getData(@Query("category") String category);
}
