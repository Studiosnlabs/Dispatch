package com.example.dispatch.RegularDelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.dispatch.R;
import com.example.dispatch.utils.RecyclerAdapter;
import com.example.dispatch.utils.RegularDriversFeed;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class RegularDriversActivity extends AppCompatActivity {

    Bitmap PackagePostImage;
    Bitmap profileImage;
    String UsernamePost;
    String UserAddressPost;
    String UserDestinationPost;
    String MoreInfoPost;
    String VehicleTypePost;


    private RecyclerView recyclerView;
    private ArrayList<RegularDriversFeed> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_drivers);

        arrayList = new ArrayList<>();


        recyclerView = findViewById(R.id.recyclerView);

        //Do parse query with loop to add to array list

        ParseQuery<ParseObject> feedQuery =ParseQuery.getQuery("request");
        feedQuery.whereEqualTo("DispatchType","Regular");
        feedQuery.whereEqualTo("RequestStatus","Pending");
        feedQuery.whereEqualTo("VehicleType",VehicleTypePost);
        feedQuery.orderByDescending("createdAt");


//use arrays to store data and cycle through data with for loop

        feedQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e==null){
                    if (objects.size()>0){
                        for (ParseObject object:objects){
                            ParseFile file =(ParseFile) object.get("image");
                           UsernamePost= object.getString("User");
                           UserDestinationPost=object.getString("Destination");

                            ParseQuery<ParseObject> UserPhotoQuery =ParseQuery.getQuery("User");
                            UserPhotoQuery.whereEqualTo("User",UsernamePost);

                            ParseQuery<ParseObject> DriverUserQuery =ParseQuery.getQuery("User");
                            DriverUserQuery.whereEqualTo("User",ParseUser.getCurrentUser());
                            VehicleTypePost=object.getString("VehicleType");


                            ParseFile UserPhoto=(ParseFile) object.get("userProfile");

                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e==null && data !=null){

                                        PackagePostImage = BitmapFactory.decodeByteArray(data,0,data.length);

                                    }
                                }
                            });

                            UserPhoto.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if(e==null && data!=null){
                                        profileImage=BitmapFactory.decodeByteArray(data,0,data.length);
                                    }
                                }
                            });

                            arrayList.add(new RegularDriversFeed(PackagePostImage,profileImage,UsernamePost,UserAddressPost,UserDestinationPost,MoreInfoPost));



                        }

                    }


                }
            }
        });



        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(arrayList);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RegularDriversActivity.this));


    }
}