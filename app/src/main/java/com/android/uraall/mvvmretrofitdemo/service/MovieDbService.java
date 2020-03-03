package com.android.uraall.mvvmretrofitdemo.service;

import com.android.uraall.mvvmretrofitdemo.model.TmdbApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDbService {

    @GET("movie/popular")
    Call<TmdbApiResponse> getPopularMovies(@Query("api_key") String apiKey);

}
