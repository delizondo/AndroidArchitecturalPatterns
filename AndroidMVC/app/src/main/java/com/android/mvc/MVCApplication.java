package com.android.mvc;

import android.app.Application;

import com.omdb.model.api.ApiClient;
import com.omdb.model.api.ApiInterface;


/**
 * Created by david on 4/17/17.
 */

public class MVCApplication extends Application {

    private static MVCApplication sInstance;

    private ApiInterface mApiInterface;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mApiInterface = ApiClient.getInstance().create(ApiInterface.class);
    }

    public ApiInterface getAPI() {
        return mApiInterface;
    }

    public static MVCApplication getInstance() {
        return sInstance;
    }
}
