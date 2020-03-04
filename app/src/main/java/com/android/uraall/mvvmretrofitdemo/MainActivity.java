package com.android.uraall.mvvmretrofitdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.android.uraall.mvvmretrofitdemo.model.MovieApiResponse;
import com.android.uraall.mvvmretrofitdemo.service.MovieApiService;
import com.android.uraall.mvvmretrofitdemo.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPopularMovies();
    }

    public void getPopularMovies() {

        MovieApiService movieApiService = RetrofitInstance.getService();

        Call<MovieApiResponse> call = movieApiService
                .getPopularMovies(getString(R.string.api_key));
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call,
                                   Response<MovieApiResponse> response) {

                MovieApiResponse movieApiResponse =
                        response.body();

            }

            @Override
            public void onFailure(Call<MovieApiResponse> call,
                                  Throwable t) {

            }
        });

    }
}
