package com.example.dispatch.ExpressDelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dispatch.R;
import com.example.dispatch.utils.OrderModule;
import com.example.dispatch.utils.SendersLocationModule;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class ExpressFormActivity extends AppCompatActivity {

    private static final String TAG = "ExpressformActivity";

    EditText packageDescription;
    EditText recipientName;
    EditText recipientPhone;
    String vehicleType;
    ParseGeoPoint sendersLocation;
    Spinner dropdown;
    OrderModule expressOrder;
    EditText destination;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_form);


        packageDescription =findViewById(R.id.PackageDescriptionET);
        recipientName=findViewById(R.id.RecipientName);
        recipientPhone=findViewById(R.id.RecipientPhone);
        dropdown = findViewById(R.id.vehicleType);
        destination=findViewById(R.id.PackageDestination);


        String[] items = new String[]{"Select a type of vehicle","Bicycle", "Motor bike", "Small Car","Caravan","Pick up"};

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//        dropdown.setAdapter(adapter);

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item,items){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        dropdown.setAdapter(spinnerArrayAdapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }






    public void expressOrder(View view){

       


        Log.d(TAG, "expressOrder: uploading request");


        String expressPackageDescription= packageDescription.getText().toString();
        String expressRecipientName=recipientName.getText().toString();
        String expressVehicleType=dropdown.getSelectedItem().toString();
        String expressRecipientPhone=recipientPhone.getText().toString();
        String expressRecipientLocation=destination.getText().toString();




       if (!(expressPackageDescription.matches("") || expressRecipientName.matches("") || expressVehicleType.matches("Select a type of vehicle") || expressRecipientPhone.matches("") || expressRecipientLocation.matches("")) ){



           ParseQuery<ParseObject> query = ParseQuery.getQuery("Requests");
           Intent intent= getIntent();
           query.whereEqualTo("User",intent.getStringExtra("username"));


           query.findInBackground(new FindCallback<ParseObject>() {
               @Override
               public void done(List<ParseObject> objects, ParseException e) {
                   if (e == null){

                       Log.d(TAG, "done: uploading request data");

                       if (objects.size()>0){
                           for (ParseObject object:objects){

                               object.put("PackageDescription",expressPackageDescription);
                               object.put("RecipientName",expressRecipientName);
                               object.put("VehicleType",expressVehicleType);
                               object.put("RecipientPhone",expressRecipientPhone);


                               object.saveInBackground(new SaveCallback() {
                                   @Override
                                   public void done(ParseException e) {
                                       if(e == null){
                                           Log.d(TAG, "done: Upload successful");
                                       }
                                       else{
                                           Log.d(TAG, "done: Upload failed");
                                           Toast.makeText(ExpressFormActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               });


                           }

                       }



                   }
               }
           });








       } else {
            Toast.makeText(this, "You must fill the whole form", Toast.LENGTH_SHORT).show();
        }


        Intent intent = new Intent(this, ExpressDestinationMap.class);
        startActivity(intent);

    }


















}