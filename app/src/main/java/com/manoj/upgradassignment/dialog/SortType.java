package com.manoj.upgradassignment.dialog;

/**
 * Created by manoj on 22/06/16.
 */
public enum SortType {
    POPULARITY("popularity.desc"),
    HIGHEST_RATED("vote_average.desc");

    private String sortType;

    SortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSortType() {
        return sortType;
    }

    public static SortType fromOrdinal(int which) {
        for (SortType sortType : SortType.values()) {
            if (sortType.ordinal() == which) {
                return sortType;
            }
        }
        //defaul
        return POPULARITY;
    }
}
