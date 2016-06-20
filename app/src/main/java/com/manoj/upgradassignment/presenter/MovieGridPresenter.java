package com.manoj.upgradassignment.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.manoj.upgradassignment.Constants;
import com.manoj.upgradassignment.MovieDatabase;
import com.manoj.upgradassignment.model.Movie;
import com.manoj.upgradassignment.model.MovieListPage;
import com.manoj.upgradassignment.utils.rest.RequestCallback;
import com.manoj.upgradassignment.utils.rest.Rest;
import com.manoj.upgradassignment.view.MovieGridView;

import java.lang.ref.WeakReference;

/**
 * Created by manoj on 20/06/16.
 */
public class MovieGridPresenter {

    private WeakReference<MovieGridView> view;

    public MovieGridPresenter(MovieGridView movieGridView) {
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
        if (MovieDatabase.getInstance().isDataComplete()) {
            return;
        }
        if (MovieDatabase.getInstance().getLastKnowPage() == 0) {
            loadFirstPage();
        } else {
            loadNextPage();
        }
    }

    private void loadFirstPage() {
        view().showLoading();
        loadNextPage();
    }

    private void loadNextPage() {
        int pageNo = MovieDatabase.getInstance().getNextPage();
        fetchData(pageNo);
    }

    private void fetchData(final int pageNo) {
        Log.d("manoj", "fetch result for page : " + pageNo);
        int sortType = MovieDatabase.getInstance().getSortOrder();
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
        MovieDatabase.getInstance().insertPageData(movieList);
        view().onMovieGridDataChanged();
    }

    public void onDataLoadFailed(int pageNo, String error) {
        if (pageNo == 1) {
            view().showError();
        }
    }

    public void onSortClicked() {
        view().showSortDialog();
    }

    public void onMovieSelected(int position) {
        Movie data = MovieDatabase.getInstance().getItem(position);
        view().openMovieDetailPage(data);
    }

    public void onSortOrderChange(int type) {
        //sort order change
        if (MovieDatabase.getInstance().getSortOrder() != type) {
            MovieDatabase.getInstance().setSortOrder(type);
            MovieDatabase.getInstance().clearData();
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
