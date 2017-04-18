package com.android.mvp.presenter;

import com.omdb.model.bo.MovieObject;

import java.util.List;

/**
 * Created by david on 4/18/17.
 */

public interface SearchPresenterView {

    void addMovies(List<MovieObject> movies);

    void resetAdapter();
}
