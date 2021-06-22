package com.example.dispatch.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dispatch.ExpressDelivery.ExpressActivity;
import com.example.dispatch.R;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";


    public void redirectActivity(){

        if (ParseUser.getCurrentUser().getString("userOrDriver").equals("user")) {

            Intent intent = new Intent(getApplicationContext(),ExpressActivity.class);
            startActivity(intent);


        } else {
            Log.d(TAG, "redirectActivity: this user was logged in as a driver but i have not created the driver activity yet");
        }

    }

    public void userLogin(View view){


        Log.d(TAG, "userLogin: user login successful");

       // ParseUser.getCurrentUser().put("isDriver",false);

        Log.d(TAG, "userLogin: redirecting as user");

        Intent intent= new Intent(getApplicationContext(), UserLoginActivity.class);
        startActivity(intent);

    }

    public void driverLogin(View view){
        Log.d(TAG, "driverLogin: driver login successful");

       // ParseUser.getCurrentUser().put("isDriver",true);

        Log.d(TAG, "driverLogin: redirecting as Driver");

        Intent intent= new Intent(getApplicationContext(), Driver_Login_Activity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


/*

        if (ParseUser.getCurrentUser()==null){

            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e==null){
                        Log.d(TAG, "done: Anonymous login successful");
                        

                    } else{
                        Log.d(TAG, "done: Anonymous Login failed");
                    }
                }
            });

        } else{

            if (ParseUser.getCurrentUser().get("userOrDriver") !=null){

                    Log.d(TAG, "onCreate: redirecting as"+ParseUser.getCurrentUser().get("userOrDriver"));
                    redirectActivity();


            }
        }*/

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
