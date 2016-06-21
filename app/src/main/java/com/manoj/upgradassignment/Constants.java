package com.manoj.upgradassignment;


import android.net.Uri;

/**
 * Created by manoj on 21/06/16.
 */
public class Constants {
    public static final String API_KEY = "173dc43165faf7b4e44e8c6e592f5050";
    public static final String BASE_MOVIE_DB_URL = "http://api.themoviedb.org/3/discover/movie?";
    public static final String BASE_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    public static String getMovieListUrl(int pageNo, int type) {
        String sortType = type == 0 ? "popularity.desc" : "vote_average.desc";
        String url = BASE_MOVIE_DB_URL;
        url += "api_key=" + API_KEY;
        url += "&sort_by=" + sortType;
        url += "&page=" + String.valueOf(pageNo);
        /*Uri.Builder builder = Uri.parse(BASE_MOVIE_DB_URL).buildUpon();
        builder.appendQueryParameter("api_key", API_KEY);
        builder.appendQueryParameter("sort_by", sortType);
        builder.appendQueryParameter("page", String.valueOf(pageNo));
        return builder.build().toString();*/
        return url;
    }


    public static String getImageUrl(String path) {
        Uri.Builder builder = Uri.parse(BASE_IMAGE_PATH).buildUpon();
        builder.appendEncodedPath(path);
        return builder.build().toString();
    }
}
