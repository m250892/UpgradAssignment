package com.manoj.upgradassignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.manoj.upgradassignment.model.Movie;
import com.manoj.upgradassignment.utils.ImageLoader;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String PARAM_MOVIE_DATA = "param_movie_data";
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        if (getIntent() != null) {
            movie = (Movie) getIntent().getSerializableExtra(PARAM_MOVIE_DATA);
        }

        if (movie == null) {
            throw new IllegalStateException("movie data is null");
        }
        initViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        ImageView poster = (ImageView) findViewById(R.id.movie_backdrop_img);
        TextView overview = (TextView) findViewById(R.id.movie_over_view);
        ImageLoader.getInstance().loadImage(movie.getBackDropUrl(), poster);
        overview.setText(movie.getOverview());
        getSupportActionBar().setTitle(movie.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
