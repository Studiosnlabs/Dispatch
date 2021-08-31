package com.example.dispatch.ExpressDelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.dispatch.R;

import java.util.ArrayList;

public class ExpressReceiptActivity extends AppCompatActivity {
    TextView PackageDescription;
    TextView RecipientName;
    TextView RecipientPhone;
    TextView PackageDestination;
    TextView VehicleType;

    String PackageDescriptionH;
    String RecipientNameH;
    String RecipientPhoneH;
    String PackageDestinationH;
    String VehicleTypeH;

    ArrayList<String> PackageDescriptionA =new ArrayList<>();
    ArrayList<String> RecipientNameA =new ArrayList<>();
    ArrayList<String> RecipientPhoneA=new ArrayList<>();
    ArrayList<String> PackageDestinationA=new ArrayList<>();
    ArrayList<String> VehicleTypeA=new ArrayList<>();

    public String getPackageDescriptionH() {
        return PackageDescriptionH;
    }

    public void setPackageDescriptionH(String packageDescriptionH) {
        PackageDescriptionH = packageDescriptionH;
    }

    public String getRecipientNameH() {
        return RecipientNameH;
    }

    public void setRecipientNameH(String recipientNameH) {
        RecipientNameH = recipientNameH;
    }

    public String getRecipientPhoneH() {
        return RecipientPhoneH;
    }

    public void setRecipientPhoneH(String recipientPhoneH) {
        RecipientPhoneH = recipientPhoneH;
    }

    public String getPackageDestinationH() {
        return PackageDestinationH;
    }

    public void setPackageDestinationH(String packageDestinationH) {
        PackageDestinationH = packageDestinationH;
    }

    public String getVehicleTypeH() {
        return VehicleTypeH;
    }

    public void setVehicleTypeH(String vehicleTypeH) {
        VehicleTypeH = vehicleTypeH;
    }



    private static final String TAG = "ExpressUserAuction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_receipt);

//        PackageDescription = findViewById(R.id.packageDescriptionTv);
//        RecipientName = findViewById(R.id.RecipientName);
//        RecipientPhone= findViewById(R.id.RecipientPhone);
//        PackageDestination=findViewById(R.id.PackageDestinationTV);
//        VehicleType=findViewById(R.id.vehicleTypeTV);

/*
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Request");
        query.whereEqualTo("User", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {

                    if (objects.size() > 0) {

                        for (ParseObject object : objects) {

                            PackageDescriptionA.add(object.getString("PackageDescription"));
                            RecipientNameA.add(object.getString("RecipientName"));
                            RecipientPhoneA.add(object.getString("RecipientPhone"));
                            VehicleTypeA.add(object.getString("VehicleType"));



                        }


                    }
                }
            }
        });


        PackageDescription.setText(PackageDescriptionA.get(0));
        RecipientName.setText(RecipientNameA.get(0));
        RecipientPhone.setText(RecipientPhoneA.get(0));
        VehicleType.setText(VehicleTypeA.get(0));*/

    }
}