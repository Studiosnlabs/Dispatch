package com.example.dispatch.ExpressDelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.dispatch.R;
import com.example.dispatch.ExpressUserMapsActivity;
import com.example.dispatch.utils.BottomNavigationViewHelper;
import com.example.dispatch.utils.UserLoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class ExpressActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    private static final String TAG = "ExpressActivity";
    private static final int ACTIVITY_NUM = 0;

    private Context mcontext = ExpressActivity.this;

    public void useCurrentLocation(View view) {
        Log.d(TAG, "useCurrentLocation: redirecting to map activity using currernt location");
        Intent intent = new Intent(getApplicationContext(), ExpressUserMapsActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);
        Log.d(TAG, "onCreate: started");

        ParseInstallation.getCurrentInstallation().saveInBackground();


        setupToolBar();

        setupBottomNavigationView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_logout:
                ParseUser.logOut();
                Intent intent = new Intent(ExpressActivity.this, UserLoginActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


    private void setupToolBar() {

        Toolbar toolbar = findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);


        NavigationView navigationView= findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();




    }


    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView:setting up BottomNavigation view");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mcontext, bottomNavigationViewEx);

        Menu menu = bottomNavigationViewEx.getMenu();

        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);

        menuItem.setChecked(true);
    }


}
