package com.manoj.upgradassignment;

import android.app.Application;

import com.manoj.upgradassignment.utils.ImageLoader;
import com.manoj.upgradassignment.utils.rest.Rest;


/**
 * Created by manoj on 20/06/16.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.init(getBaseContext());
        Rest.init(getBaseContext());
    }
}
