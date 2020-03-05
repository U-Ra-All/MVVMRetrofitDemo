package com.android.uraall.mvvmretrofitdemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.uraall.mvvmretrofitdemo.R;
import com.android.uraall.mvvmretrofitdemo.model.Result;

public class MovieDetailsActivity extends AppCompatActivity {

    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("movieData")) {

            result = intent.getParcelableExtra("movieData");

            Toast.makeText(this, result.getOriginalTitle(),
                    Toast.LENGTH_LONG).show();

        }

    }
}
