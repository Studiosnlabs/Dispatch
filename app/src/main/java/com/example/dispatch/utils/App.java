package com.example.dispatch.utils;

import android.app.Application;

import com.example.dispatch.R;
import com.parse.Parse;

public class App extends Application {


    public void onCreate() {
        super.onCreate();
        //FIRST SAVE US UNDER A NEW NAME BEFORE MAKING ANY CHANGES, DO IT NOW!!
        // Make sure to change ids in strings for new apps.

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()

        );


    }



}
