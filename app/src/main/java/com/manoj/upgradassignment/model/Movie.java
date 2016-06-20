package com.manoj.upgradassignment.model;

import com.google.gson.annotations.SerializedName;
import com.manoj.upgradassignment.Constants;

import java.io.Serializable;

/**
 * Created by manoj on 20/06/16.
 */
public class Movie implements Serializable {
    private int id;
    @SerializedName("poster_path")
    private String posterPath;
    private String overview;
    @SerializedName("release_date")
    private String releaseData;
    private String title;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("vote_average")
    private double voteAverage;

    private String posterUrl;
    private String backdropUrl;

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseData() {
        return releaseData;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getPosterUrl() {
        if (posterUrl == null && posterPath != null) {
            posterUrl = Constants.getImageUrl(posterPath);
        }
        return posterUrl;
    }

    public String getBackDropUrl() {
        if (backdropUrl == null && backdropPath != null) {
            backdropUrl = Constants.getImageUrl(backdropPath);
        }
        return backdropUrl;
    }
}
