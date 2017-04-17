package com.omdb.model.bo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by david on 4/17/17.
 */

public class MovieObject {

    @SerializedName("Title")
    private String mTitle;

    @SerializedName("Year")
    private String mYear;

    @SerializedName("Poster")
    private String mPoster;

    @SerializedName("imdbID")
    private String mImdbID;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String poster) {
        mPoster = poster;
    }

    public String getImdbID() {
        return mImdbID;
    }

    public void setImdbID(String imdbID) {
        mImdbID = imdbID;
    }
}
