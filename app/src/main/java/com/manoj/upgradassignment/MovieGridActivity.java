package com.manoj.upgradassignment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.manoj.upgradassignment.dialog.SortDialog;
import com.manoj.upgradassignment.model.Movie;
import com.manoj.upgradassignment.presenter.MovieGridPresenter;
import com.manoj.upgradassignment.view.AdapterView;
import com.manoj.upgradassignment.view.MovieGridAdapter;
import com.manoj.upgradassignment.view.MovieGridView;

public class MovieGridActivity extends AppCompatActivity implements MovieGridView, AdapterView, SortDialog.ISortDialogCallback, View.OnClickListener {

    private static final int MOVIE_GRID_COLUMN_COUNT_PORTRAIT = 2;
    private static final int MOVIE_GRID_COLUMN_COUNT_LANDSCAPE = 3;
    private View progressBar;
    private View errorLayout;
    private RecyclerView movieGridView;
    private MovieGridPresenter presenter;
    private MovieGridAdapter movieGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new MovieGridPresenter(this);
        initViews();

        //After all intialize complete
        if (MovieDatabase.getInstance().getLastKnowPage() == 0) {
            presenter.loadData();
        }
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_bar);
        errorLayout = findViewById(R.id.error_layout);
        movieGridView = (RecyclerView) findViewById(R.id.movie_grid_view);
        movieGridView.setLayoutManager(new GridLayoutManager(this, getColumnCount()));
        movieGridView.setHasFixedSize(true);
        movieGridAdapter = new MovieGridAdapter(this, this);
        movieGridView.setAdapter(movieGridAdapter);

        findViewById(R.id.retry_button).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_grid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort) {
            presenter.onSortClicked();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hiderLoadingView() {
        if (isLoadingViewVisible()) {
            progressBar.setVisibility(View.GONE);
        }
    }

    public boolean isLoadingViewVisible() {
        return progressBar.getVisibility() == View.VISIBLE;
    }

    private void hideMovieGridView() {
        if (isMovieGridViewVisible()) {
            movieGridView.setVisibility(View.GONE);
        }
    }

    public boolean isMovieGridViewVisible() {
        return movieGridView.getVisibility() == View.VISIBLE;
    }

    private void hideErrorLayout() {
        if (isErrorViewVisible()) {
            errorLayout.setVisibility(View.GONE);
        }
    }

    public boolean isErrorViewVisible() {
        return errorLayout.getVisibility() == View.VISIBLE;
    }

    @Override
    public void showLoading() {
        hideErrorLayout();
        hideMovieGridView();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        hiderLoadingView();
        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMovieGrid() {
        hiderLoadingView();
        if (movieGridView.getVisibility() == View.GONE) {
            movieGridView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showSortDialog() {
        //show dialog
        SortDialog sortDialog = new SortDialog();
        sortDialog.show(getSupportFragmentManager(), SortDialog.class.getCanonicalName());
    }

    @Override
    public void onMovieGridDataChanged() {
        if (movieGridAdapter != null) {
            movieGridAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void openMovieDetailPage(Movie data) {
        Log.d("manoj", "open movie detail page");
        Intent intent = new Intent(this, MovieDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MovieDetailActivity.PARAM_MOVIE_DATA, data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onLastItemDisplayed() {
        Log.d("manoj", "last item displayed called");
        presenter.loadData();
    }

    @Override
    public void onItemClicked(int position) {
        presenter.onMovieSelected(position);
    }

    private boolean isPortraitMode() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public int getColumnCount() {
        return isPortraitMode() ? MOVIE_GRID_COLUMN_COUNT_PORTRAIT : MOVIE_GRID_COLUMN_COUNT_LANDSCAPE;
    }

    @Override
    public void onSortTypeSelected(int type) {
        presenter.onSortOrderChange(type);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retry_button:
                 onRetryButtonClick();
                break;
        }
    }

    private void onRetryButtonClick(){
        presenter.onRetryBtnClick();
    }
}
