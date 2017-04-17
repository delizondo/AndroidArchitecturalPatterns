package com.omdb.model.bo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by david on 4/17/17.
 */

public class MovieDetail {

    @SerializedName("Title")
    private String mTitle;

    @SerializedName("Year")
    private String mYear;

    @SerializedName("Genre")
    private String mGenre;

    @SerializedName("Director")
    private String mDirector;

    @SerializedName("Writer")
    private String mWriter;

    @SerializedName("Actors")
    private String mActors;

    @SerializedName("Plot")
    private String mPlot;

    @SerializedName("Poster")
    private String mPoster;

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

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getDirector() {
        return mDirector;
    }

    public void setDirector(String director) {
        mDirector = director;
    }

    public String getWriter() {
        return mWriter;
    }

    public void setWriter(String writer) {
        mWriter = writer;
    }

    public String getActors() {
        return mActors;
    }

    public void setActors(String actors) {
        mActors = actors;
    }

    public String getPlot() {
        return mPlot;
    }

    public void setPlot(String plot) {
        mPlot = plot;
    }

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String poster) {
        mPoster = poster;
    }
}
