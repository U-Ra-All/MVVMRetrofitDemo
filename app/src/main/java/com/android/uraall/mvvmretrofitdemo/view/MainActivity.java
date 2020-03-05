package com.android.uraall.mvvmretrofitdemo.view;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.uraall.mvvmretrofitdemo.R;
import com.android.uraall.mvvmretrofitdemo.adapter.ResultAdapter;
import com.android.uraall.mvvmretrofitdemo.model.MovieApiResponse;
import com.android.uraall.mvvmretrofitdemo.model.Result;
import com.android.uraall.mvvmretrofitdemo.service.MovieApiService;
import com.android.uraall.mvvmretrofitdemo.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> results;
    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPopularMovies();

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getPopularMovies();

            }
        });
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

                if (movieApiResponse != null &&
                        movieApiResponse.getResults() != null) {

                    results = (ArrayList<Result>) movieApiResponse
                            .getResults();
                    fillRecyclerView();
                    swipeRefreshLayout.setRefreshing(false);

                }

            }

            @Override
            public void onFailure(Call<MovieApiResponse> call,
                                  Throwable t) {

            }
        });

    }

    private void fillRecyclerView() {

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ResultAdapter(this, results);

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(
                    new GridLayoutManager(this, 2));

        } else {

            recyclerView.setLayoutManager(
                    new GridLayoutManager(this, 4));

        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
