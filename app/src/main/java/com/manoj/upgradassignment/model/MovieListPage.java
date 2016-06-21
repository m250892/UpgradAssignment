package com.manoj.upgradassignment.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by manoj on 21/06/16.
 */
public class MovieListPage {
    private int page;
    @SerializedName("results")
    private List<Movie> movies;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
