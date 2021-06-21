package com.example.dispatch.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.dispatch.BatchDelivery.BatchActivity;
import com.example.dispatch.ExpressDelivery.ExpressActivity;
import com.example.dispatch.R;
import com.example.dispatch.RegularDelivery.RegularActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setIconVisibility(false);
        bottomNavigationViewEx.setTextSize(12);

    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx viewEx){
        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_express:
                        Intent intent1=new Intent(context, ExpressActivity.class);
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_regular:
                        Intent intent2=new Intent(context, RegularActivity.class);
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_batch:
                        Intent intent3=new Intent(context, BatchActivity.class);
                        context.startActivity(intent3);
                        break;


                }
                return false;
            }
        });
    }

}
