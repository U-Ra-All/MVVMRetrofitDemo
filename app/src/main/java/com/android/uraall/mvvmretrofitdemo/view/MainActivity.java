package com.android.uraall.mvvmretrofitdemo.view;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.uraall.mvvmretrofitdemo.R;
import com.android.uraall.mvvmretrofitdemo.adapter.ResultAdapter;
import com.android.uraall.mvvmretrofitdemo.databinding.ActivityMainBinding;
import com.android.uraall.mvvmretrofitdemo.model.MovieApiResponse;
import com.android.uraall.mvvmretrofitdemo.model.Result;
import com.android.uraall.mvvmretrofitdemo.service.MovieApiService;
import com.android.uraall.mvvmretrofitdemo.service.RetrofitInstance;
import com.android.uraall.mvvmretrofitdemo.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private PagedList<Result> results;
    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);

        getPopularMovies();

        swipeRefreshLayout = activityMainBinding.swiperefresh;
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

//        mainActivityViewModel.getAllMovieData().observe(this,
//                new Observer<List<Result>>() {
//                    @Override
//                    public void onChanged(List<Result> resultList) {
//                        results = (ArrayList<Result>) resultList;
//                        fillRecyclerView();
//                    }
//                });

        mainActivityViewModel.getPagedListLiveData().observe(this,
                new Observer<PagedList<Result>>() {
                    @Override
                    public void onChanged(PagedList<Result> resultList) {

                        results = resultList;
                        fillRecyclerView();

                    }
                });

    }

    private void fillRecyclerView() {

        recyclerView = activityMainBinding.recyclerView;
        adapter = new ResultAdapter(this);
        adapter.submitList(results);

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
        swipeRefreshLayout.setRefreshing(false);
    }
}
