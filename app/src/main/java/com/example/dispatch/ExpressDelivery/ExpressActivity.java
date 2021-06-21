package com.example.dispatch.ExpressDelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dispatch.R;
import com.example.dispatch.ExpressUserMapsActivity;
import com.example.dispatch.utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.parse.ParseInstallation;

public class ExpressActivity extends AppCompatActivity {

    private static final String TAG = "ExpressActivity";
    private static final int ACTIVITY_NUM=0;

    private Context mcontext= ExpressActivity.this;
    
    public void useCurrentLocation(View view){
        Log.d(TAG, "useCurrentLocation: redirecting to map activity using currernt location");
        Intent intent= new Intent(getApplicationContext(), ExpressUserMapsActivity.class);
        startActivity(intent);
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);

        ParseInstallation.getCurrentInstallation().saveInBackground();

        Log.d(TAG, "onCreate: started");

         setupBottomNavigationView();
    }

    private void setupBottomNavigationView(){
        Log.d(TAG,"setupBottomNavigationView:setting up BottomNavigation view");
        BottomNavigationViewEx bottomNavigationViewEx=(BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mcontext,bottomNavigationViewEx);

        Menu menu = bottomNavigationViewEx.getMenu();

        MenuItem menuItem= menu.getItem(ACTIVITY_NUM);

        menuItem.setChecked(true);
    }



}
