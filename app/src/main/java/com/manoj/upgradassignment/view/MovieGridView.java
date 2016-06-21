package com.manoj.upgradassignment.view;

import com.manoj.upgradassignment.model.Movie;

/**
 * Created by manoj on 20/06/16.
 */
public interface MovieGridView {

    void showLoading();

    void showError();

    void showMovieGrid();

    void showSortDialog();

    void onMovieGridDataChanged();

    void openMovieDetailPage(Movie data);

    boolean isLoadingViewVisible();

    boolean isMovieGridViewVisible();

    boolean isErrorViewVisible();

    void showTaost(String error);
}
