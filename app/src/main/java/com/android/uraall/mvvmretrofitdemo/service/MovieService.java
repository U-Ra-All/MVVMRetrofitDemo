package com.android.uraall.mvvmretrofitdemo.service;

import com.android.uraall.mvvmretrofitdemo.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/popular")
    Call<ApiResponse> getPopularMovies(@Query("api_key") String apiKey);

}
