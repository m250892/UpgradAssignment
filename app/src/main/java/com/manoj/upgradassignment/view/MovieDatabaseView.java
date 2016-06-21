package com.manoj.upgradassignment.view;

import com.manoj.upgradassignment.model.Movie;
import com.manoj.upgradassignment.model.MovieListPage;

import java.util.List;

/**
 * Created by manoj on 21/06/16.
 */
public interface MovieDatabaseView {

    List<Movie> getMovies();

    int getLastKnowPage();

    void insertPageData(MovieListPage movieListPage);

    boolean isDataComplete();

    int getNextPage();

    void clearData();

    int getDataSize();

    Movie getItem(int position);

    int getSortOrder();

    void setSortOrder(int sortOrder);
}
