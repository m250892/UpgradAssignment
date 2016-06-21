package com.manoj.upgradassignment;


import android.net.Uri;

import com.manoj.upgradassignment.dialog.SortType;

/**
 * Created by manoj on 21/06/16.
 */
public class Constants {
    public static final String API_KEY = "173dc43165faf7b4e44e8c6e592f5050";
    public static final String BASE_MOVIE_DB_URL = "http://api.themoviedb.org/3/discover/movie?";
    public static final String BASE_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    public static String getMovieListUrl(int pageNo, SortType type) {
        Uri.Builder builder = Uri.parse(BASE_MOVIE_DB_URL).buildUpon();
        builder.appendQueryParameter("api_key", API_KEY);
        builder.appendQueryParameter("sort_by", type.getSortType());
        builder.appendQueryParameter("page", String.valueOf(pageNo));
        return builder.build().toString();
    }


    public static String getImageUrl(String path) {
        Uri.Builder builder = Uri.parse(BASE_IMAGE_PATH).buildUpon();
        builder.appendEncodedPath(path);
        return builder.build().toString();
    }
}
