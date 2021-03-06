package com.manoj.upgradassignment;

import com.manoj.upgradassignment.model.Movie;
import com.manoj.upgradassignment.model.MovieListPage;
import com.manoj.upgradassignment.dialog.SortType;
import com.manoj.upgradassignment.view.MovieDatabaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manoj on 20/06/16.
 */
public class MovieDatabase implements MovieDatabaseView {
    private static MovieDatabase instance;
    private List<Movie> movies;
    private int lastKnowPage;

    private int totalPages;
    private SortType sortOrder;

    private MovieDatabase() {
        movies = new ArrayList<>();
        sortOrder = SortType.POPULARITY;
        totalPages = -1;
        lastKnowPage = 0;
    }

    public static MovieDatabase getInstance() {
        if (instance == null) {
            instance = new MovieDatabase();
        }
        return instance;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public int getLastKnowPage() {
        return lastKnowPage;
    }

    public void insertPageData(MovieListPage movieListPage) {
        int currentPage = movieListPage.getPage();
        if (currentPage == lastKnowPage + 1) {
            this.lastKnowPage = currentPage;
            this.movies.addAll(movieListPage.getMovies());
        }
        if (currentPage == 1) {
            totalPages = movieListPage.getTotalPages();
        }
    }

    public boolean isDataComplete() {
        return lastKnowPage == totalPages;
    }

    public int getNextPage() {
        return lastKnowPage + 1;
    }

    public void clearData() {
        lastKnowPage = 0;
        totalPages = -1;
        movies.clear();
    }

    public int getDataSize() {
        return getMovies().size();
    }

    public Movie getItem(int position) {
        return getMovies().get(position);
    }

    public SortType getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortType sortOrder) {
        this.sortOrder = sortOrder;
    }
}
