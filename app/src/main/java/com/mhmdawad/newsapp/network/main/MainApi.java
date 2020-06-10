package com.mhmdawad.newsapp.network.main;

import com.mhmdawad.newsapp.models.Response;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

    @GET("top-headlines")
    Flowable<Response> getTopHead(
            @Query("country") String country,
            @Query("page") int page,
            @Query("pageSize") int size,
            @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Flowable<Response> getCategoryData(
            @Query("category") String query,
            @Query("page") int page,
            @Query("pageSize") int size,
            @Query("apiKey") String apiKey);

    @GET("everything")
    Flowable<Response> getEverythingSearch(
            @Query("q") String query,
            @Query("page") int page,
            @Query("pageSize") int size,
            @Query("apiKey") String apiKey);


}
