package com.manoj.upgradassignment.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.manoj.upgradassignment.Constants;
import com.manoj.upgradassignment.MovieDatabase;
import com.manoj.upgradassignment.model.Movie;
import com.manoj.upgradassignment.model.MovieListPage;
import com.manoj.upgradassignment.dialog.SortType;
import com.manoj.upgradassignment.utils.rest.RequestCallback;
import com.manoj.upgradassignment.utils.rest.Rest;
import com.manoj.upgradassignment.view.MovieDatabaseView;
import com.manoj.upgradassignment.view.MovieGridView;

import java.lang.ref.WeakReference;

/**
 * Created by manoj on 20/06/16.
 */
public class MovieGridPresenter {

    private WeakReference<MovieGridView> view;
    private MovieDatabaseView movieDatabase;

    public MovieGridPresenter(MovieGridView movieGridView) {
        movieDatabase = MovieDatabase.getInstance();
        bindView(movieGridView);
    }

    public void bindView(@NonNull MovieGridView view) {
        this.view = new WeakReference<>(view);
    }

    public void unbindView() {
        this.view = null;
    }

    protected MovieGridView view() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }

    public void loadData() {
        Log.d("manoj", "load data called");
        if (movieDatabase.isDataComplete()) {
            return;
        }
        if (movieDatabase.getLastKnowPage() == 0) {
            loadFirstPage();
        } else {
            loadNextPage();
        }
    }

    public void loadFirstPage() {
        view().showLoading();
        loadNextPage();
    }

    private void loadNextPage() {
        int pageNo = movieDatabase.getNextPage();
        fetchData(pageNo);
    }

    private void fetchData(final int pageNo) {
        Log.d("manoj", "fetch result for page : " + pageNo);
        SortType sortType = movieDatabase.getSortOrder();
        String url = Constants.getMovieListUrl(pageNo, sortType);
        Rest.GET().load(url).as(MovieListPage.class).withCallback(new RequestCallback<MovieListPage>() {
            @Override
            public void onRequestSuccess(MovieListPage data) {
                onDataLoadSuccess(pageNo, data);
            }

            @Override
            public void onRequestError(String error) {
                onDataLoadFailed(pageNo, error);
            }
        });
    }

    public void onDataLoadSuccess(int pageNo, MovieListPage movieList) {
        //if first page than hide loaded and show recycler view
        if (pageNo == 1) {
            view().showMovieGrid();
        }
        movieDatabase.insertPageData(movieList);
        view().onMovieGridDataChanged();
    }

    public void onDataLoadFailed(int pageNo, String error) {
        if (pageNo == 1) {
            view().showError();
        }
        showToast(error);
    }

    private void showToast(String error) {
        view().showTaost(error);
    }

    public void onSortClicked() {
        view().showSortDialog();
    }

    public void onMovieSelected(int position) {
        Movie data = movieDatabase.getItem(position);
        view().openMovieDetailPage(data);
    }

    public void onSortOrderChange(SortType type) {
        //sort order change
        if (movieDatabase.getSortOrder() != type) {
            movieDatabase.setSortOrder(type);
            movieDatabase.clearData();
            view().onMovieGridDataChanged();
            loadFirstPage();
        } else {
            //do nothing
        }
    }

    public void onRetryBtnClick() {
        loadData();
    }
}
