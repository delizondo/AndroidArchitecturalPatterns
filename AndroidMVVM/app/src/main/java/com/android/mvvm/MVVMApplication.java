package com.android.mvvm;

import android.app.Application;

import com.omdb.model.api.ApiClient;
import com.omdb.model.api.ApiInterface;

/**
 * Created by david on 4/18/17.
 */

public class MVVMApplication extends Application {

    private static MVVMApplication sInstance;


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

    public static MVVMApplication getInstance() {
        return sInstance;
    }
}
