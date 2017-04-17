package com.omdb.model.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 4/17/17.
 */

public class ApiClient {

    private static Retrofit sRetrofit;

    private static final String ENDPOINT = "http://www.omdbapi.com/";

    public static Retrofit getInstance() {

        if (sRetrofit == null) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

            okHttpClient.addInterceptor(loggingInterceptor);

            sRetrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.build())
                    .build();
        }
        return sRetrofit;
    }
}
