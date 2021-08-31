package com.example.dispatch.ExpressDelivery;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dispatch.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.IOException;
import java.util.List;

public class ExpressDestinationMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = "DestinationMap";

    LocationManager locationManager;
    LocationListener locationListener;
    Boolean requestActive = false;
    Handler handler = new Handler();
    TextView infoText;
    Boolean driverActive=false;

    Address DestinationAddress;

    public void setDestinationAddress(Address destinationAddress) {
        DestinationAddress = destinationAddress;
    }



    public Address getDestinationAddress() {
        return DestinationAddress;
    }



    SearchView searchView;





    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==1){



            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                    Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    UpdateMap(lastKnownLocation);}

            }
        }

    }



    public void UpdateMap(Location location){

        if (driverActive == false) {
            LatLng userlocation = new LatLng(location.getLatitude(), location.getLongitude());

            mMap.clear();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userlocation, 20));
            mMap.addMarker(new MarkerOptions().position(userlocation).title("Your Location"));
        }
    }




    public void EnableUseLocation(){

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                UpdateMap(location);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_user_destination_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Button ConfirmButton = findViewById(R.id.confirm_button);

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NextIntent();

              new Thread(new Runnable() {
                  @Override
                  public void run() {

                      confirmDestination();
                      System.out.println("button Clicked!!");
                  }
              }).start();



            }


        });




    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;




        if (Build.VERSION.SDK_INT<23){
            Toast.makeText(this, "Update your android, this version is obsolete", Toast.LENGTH_SHORT).show();
        }
        else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);

            }
            else{

                SearchView searchView=findViewById(R.id.search_location);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        String location = searchView.getQuery().toString();
                        location+=" Ghana";
                        List<Address> addressList= null;
                        if (location!=null || !location.equals("")){
                            Geocoder geocoder = new Geocoder(ExpressDestinationMap.this);
                            try {
                                addressList = geocoder.getFromLocationName(location,1);
                            }catch (IOException e){
                                Toast.makeText(ExpressDestinationMap.this, "Location not found! Please check and try again", Toast.LENGTH_SHORT).show();
                                System.out.println("ERROR LOCATION NOT FOUND");
                                e.printStackTrace();
                            }
                            Address address=addressList.get(0);
                            LatLng latLng= new LatLng(address.getLatitude(),address.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(latLng).title(location).draggable(true));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));
                            setDestinationAddress(address);
                        }

                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;

                    }
                });

                mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker marker) {

                    }

                    @Override
                    public void onMarkerDrag(Marker marker) {

                    }

                    @Override
                    public void onMarkerDragEnd(Marker marker) {
                       LatLng userDestination= marker.getPosition();



                    }
                });

            }

        }

    }


    public void NextIntent(){
        Intent intent = new Intent(this, ExpressReceiptActivity.class);
        startActivity(intent);
    }


    public void confirmDestination() {



        if (requestActive) {

            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Requests");

            query.whereEqualTo("User", ParseUser.getCurrentUser().getUsername());

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {

                        if (objects.size() > 0) {

                            for (ParseObject object : objects) {
                                object.deleteInBackground();
                            }

                            requestActive = false;

                        }
                    }
                }
            });


        } else {
            Address address= getDestinationAddress();

            ParseObject request = new ParseObject("PackageDestinations");
            request.put("User", ParseUser.getCurrentUser().getUsername());
            ParseGeoPoint Destination = new ParseGeoPoint(address.getLatitude(), address.getLongitude());
            requestActive = true;
            request.put("PackageDestination", Destination);

            request.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.d(TAG, "done: Destination Set");
                    } else {
                        System.out.println("Failed to upload" + e);
                    }
                }
            });

        }



    }

}




