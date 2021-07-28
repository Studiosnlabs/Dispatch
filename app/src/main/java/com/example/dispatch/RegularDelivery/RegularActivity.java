package com.example.dispatch.RegularDelivery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.dispatch.ExpressDelivery.ExpressActivity;
import com.example.dispatch.R;
import com.example.dispatch.utils.BottomNavigationViewHelper;
import com.example.dispatch.utils.UserLoginActivity;
import com.example.dispatch.utils.VehicleSelectAdapter;
import com.example.dispatch.utils.VehicleSelectModel;
import com.google.android.material.navigation.NavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class RegularActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "RegularActivity";
    private static final int ACTIVITY_NUM = 1;

    private Context mcontext = RegularActivity.this;

    ViewPager viewPager;
    List<VehicleSelectModel> models;
    VehicleSelectAdapter adapter;
    Button VehicleSelectButton;
    DrawerLayout drawerLayout;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular);

        Log.d(TAG, "onCreate: started");

        VehicleSelectButton = findViewById(R.id.RegularVehicleSelectBtn);



        models = new ArrayList<>();
        models.add(new VehicleSelectModel(R.drawable.dispatchbicycle, "Bicycle", "Suitable for short distances and light packages"));
        models.add(new VehicleSelectModel(R.drawable.dispatchmotorbike, "MotorBike", "Suitable for long distances and light packages"));
        models.add(new VehicleSelectModel(R.drawable.dispatchsmallcar, "Small Car", "Suitable for long distances and medium and sensitive packages"));
        models.add(new VehicleSelectModel(R.drawable.dispatchcaravan, "Caravan", "Suitable for long distances and multiple medium and sensitive packages"));
        models.add(new VehicleSelectModel(R.drawable.dispatchvan, "Van", "Suitable long distances and multiple medium or large and sensitive packages"));
        models.add(new VehicleSelectModel(R.drawable.dispatchpickup, "Pickup truck", "Suitable long distances and multiple large and sensitive packages"));


        adapter = new VehicleSelectAdapter(models, this);
        viewPager = findViewById(R.id.RegularVehicleVP);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                VehicleSelectButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), RegularFormActivity.class);
                        intent.putExtra("vehicleType", models.get(position).getTitle().toString());
                        System.out.println("VEHICLE SELECTED IS " + models.get(position).getTitle().toString());
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupBottomNavigationView();
        setupToolBar();
    }


    // this is the end of On create

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_logout:
                ParseUser.logOut();
                Intent intent = new Intent(RegularActivity.this, UserLoginActivity.class);
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

        Toolbar toolbar = findViewById(R.id.profileToolbarRegular);
        setSupportActionBar(toolbar);


        NavigationView navigationView = findViewById(R.id.nav_view);
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