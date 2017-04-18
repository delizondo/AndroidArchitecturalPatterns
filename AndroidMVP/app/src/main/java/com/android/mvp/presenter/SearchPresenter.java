package com.android.mvp.presenter;

import android.util.Log;

import com.android.mvp.MVPApplication;
import com.omdb.model.bo.MovieFeed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by david on 4/18/17.
 */

public class SearchPresenter {

    private SearchPresenterView mSearchPresenterView;

    private int mCurrentPage = 1;

    private int mTotalPages;

    private boolean mIsLoading;

    private String mMovieTitle;


    public SearchPresenter(SearchPresenterView listener) {
        mSearchPresenterView = listener;
    }


    public void searchMovies(String movieTitle) {
        mSearchPresenterView.resetAdapter();
        mMovieTitle = movieTitle;
        mCurrentPage = 1;
        requestMovies();
    }


    public void requestMovies() {
        Call<MovieFeed> call = MVPApplication.getInstance().getAPI().getImages(mMovieTitle, mCurrentPage);
        Log.d(TAG, call.request().toString());
        call.enqueue(new Callback<MovieFeed>() {
            @Override
            public void onResponse(Call<MovieFeed> call, Response<MovieFeed> response) {
                Log.d(TAG, "success");
                mIsLoading = false;
                if (response.body().getMovies() != null) {
                    mSearchPresenterView.addMovies(response.body().getMovies());
                    mTotalPages = response.body().getTotalResults();
                }
            }

            @Override
            public void onFailure(Call<MovieFeed> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public boolean isLoading() {
        return mIsLoading;
    }

    public int getCurrentPage() {
        return mCurrentPage;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void addPage(){
        mCurrentPage++;
    }

    public void setLoading(boolean isLoading){
        mIsLoading = isLoading;
    }

}
