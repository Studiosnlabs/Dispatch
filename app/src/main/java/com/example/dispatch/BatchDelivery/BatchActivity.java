package com.example.dispatch.BatchDelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dispatch.R;
import com.example.dispatch.RegularDelivery.RegularActivity;
import com.example.dispatch.RegularDelivery.RegularDriversActivity;
import com.example.dispatch.utils.BatchReyclerAdapter;
import com.example.dispatch.utils.BatchUserFeed;
import com.example.dispatch.utils.BottomNavigationViewHelper;
import com.example.dispatch.utils.RecyclerAdapter;
import com.example.dispatch.utils.RegularDriversFeed;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BatchActivity extends AppCompatActivity {

    private static final String TAG = "BatchActivity";
    private static final int ACTIVITY_NUM=2;

    private Context mcontext= BatchActivity.this;

    String CourierName;
    String Location;
    String Destination;
    String Departure;
    String Arrival;
    String Phone;
    String Slots;
    int feedCount;


    private RecyclerView recyclerView;
    private ArrayList<BatchUserFeed> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch);
        feedCount = 0;

        arrayList = new ArrayList<>();



        Log.d(TAG, "onCreate: started");

        setupBottomNavigationView();



        ParseQuery<ParseObject> batchfeedQuery = ParseQuery.getQuery("Requests");
        batchfeedQuery.whereExists("Location");
        batchfeedQuery.whereEqualTo("Status","Active");
        batchfeedQuery.orderByDescending("createdAt");

        batchfeedQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {

                            CourierName = object.getString("CourierName");
                            Destination = object.getString("Destination");
                            Date ArrivalTime=object.getDate("Arrival");
                            Arrival=ArrivalTime.toString();
                            Date DepartureTime=object.getDate("Departure");
                            Departure=DepartureTime.toString();
                            Phone=object.getString("Phone");
                            Slots=object.getString("SlotsLeft");

                            ParseGeoPoint UserAddress=object.getParseGeoPoint("Location");
                            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                            try {
                                List<Address> UserAdress1=geocoder.getFromLocation(UserAddress.getLatitude(),UserAddress.getLongitude(),1);

                                if (UserAdress1!=null && UserAdress1.size()>0){
                                    Location=UserAdress1.get(0).getAddressLine(0);

                                }
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            Log.d(TAG, "done: Data found");

                            arrayList.add(new BatchUserFeed(CourierName, Location, Destination, Departure, Arrival,Phone,Slots));


                            feedCount = feedCount + 1;


                            recyclerView = findViewById(R.id.recyclerView2);
                            BatchReyclerAdapter recyclerAdapter = new BatchReyclerAdapter(arrayList);
                            recyclerView.setAdapter(recyclerAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(BatchActivity.this));



                        }

                    }


                }
                else{

                    Log.e(TAG, "parse data error: ",e );
                }


            }
        });


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
