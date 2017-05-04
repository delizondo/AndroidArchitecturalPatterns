package com.omdb.model.api;

import com.omdb.model.bo.MovieDetail;
import com.omdb.model.bo.MovieFeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by david on 4/17/17.
 */

public interface ApiInterface {

    @GET("?y=&plot=short&r=json")
    Call<MovieFeed> getImages(@Query("s") String query, @Query("page") int page);

    @GET("plot=full")
    Call<MovieDetail> getMovieDetail(@Query("i") String imdbId);
}
