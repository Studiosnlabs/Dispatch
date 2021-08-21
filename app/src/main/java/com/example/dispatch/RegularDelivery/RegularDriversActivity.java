package com.example.dispatch.RegularDelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.example.dispatch.R;
import com.example.dispatch.utils.RecyclerAdapter;
import com.example.dispatch.utils.RegularDriversFeed;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RegularDriversActivity extends AppCompatActivity {

    private static final String TAG = "RegularDriversActivity";

    Bitmap PackageImage;
    Bitmap profileImage;
    String UsernamePost;
    String UserAddressPost;
    String UserDestinationPost;
    String MoreInfoPost;
    String VehicleTypePost;
    int feedCount;


    private RecyclerView recyclerView;
    private ArrayList<RegularDriversFeed> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_drivers);
        feedCount = 0;

        arrayList = new ArrayList<>();


        //Do parse query with loop to add to array list

        ParseQuery<ParseObject> DriverUserQuery = ParseQuery.getQuery("User");
        DriverUserQuery.whereEqualTo("User", ParseUser.getCurrentUser());

        ParseObject DriverVehicle = null;
        try {
            DriverVehicle = DriverUserQuery.getFirst();
            VehicleTypePost = DriverVehicle.getString("VehicleType");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        ParseQuery<ParseObject> feedQuery = ParseQuery.getQuery("request");
        feedQuery.whereEqualTo("DispatchType", "Regular");
        feedQuery.whereEqualTo("RequestStatus", "Pending");
        feedQuery.whereEqualTo("VehicleType", "MotorBike");
        feedQuery.orderByDescending("createdAt");

        feedQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {

                            UsernamePost = object.getString("User");
                            UserDestinationPost = object.getString("Destination");
                            ParseGeoPoint UserAddress=object.getParseGeoPoint("UserLocation");
                            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                            try {
                                List<Address> UserAdress1=geocoder.getFromLocation(UserAddress.getLatitude(),UserAddress.getLongitude(),1);

                                if (UserAdress1!=null && UserAdress1.size()>0){
                                    UserAddressPost=UserAdress1.get(0).getAddressLine(0);

                                }
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }


                            ParseFile file = (ParseFile) object.get("image");
                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e == null && data != null) {


                                        PackageImage = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        Log.d(TAG, "done: getting picture");

                                        arrayList.add(new RegularDriversFeed(PackageImage, R.drawable.ic_baseline_location_on_24, UsernamePost, UserAddressPost, UserDestinationPost));


                                        feedCount = feedCount + 1;


                                        recyclerView = findViewById(R.id.recyclerView);
                                        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(arrayList);
                                        recyclerView.setAdapter(recyclerAdapter);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(RegularDriversActivity.this));



                                    } else {
                                        Log.d(TAG, "done: image error" + e);
                                    }
                                }
                            });




                        }

                    }


                }


            }
        });


//        byte[] UserPackimg=object.getBytes("image");
//        PackageImage=BitmapFactory.decodeByteArray(UserPackimg,0,UserPackimg.length);



      /*  ParseQuery<ParseObject> UserPhotoQuery =ParseQuery.getQuery("User");
        UserPhotoQuery.whereEqualTo("User",UsernamePost);*/



    /*    ParseFile UserPhoto=(ParseFile) object.get("userProfile");

        UserPhoto.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] data, ParseException e) {
                if(e==null && data!=null){
                    profileImage=BitmapFactory.decodeByteArray(data,0,data.length);
                }
            }
        });*/


    }
}