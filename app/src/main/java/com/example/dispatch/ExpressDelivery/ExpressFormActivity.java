package com.example.dispatch.ExpressDelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dispatch.ExpressUserMapsActivity;
import com.example.dispatch.R;
import com.example.dispatch.RegularDelivery.RegularActivity;
import com.example.dispatch.RegularDelivery.RegularFormActivity;
import com.example.dispatch.utils.OrderModule;
import com.example.dispatch.utils.SendersLocationModule;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    Uri expressPackageImage;
    ImageView backToRegular;

    private ImageView PackageImage;

    private int STORAGE_PERMISSION_CODE = 1; //Identify specific requests for a permission
    private int CAMERA_PERMISSION_CODE = 2;
    private int LOCATION_PERMISSION_CODE = 3;
    private int requestCode;
    private String[] permissions;
    private int[] grantResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_form);

        if (ContextCompat.checkSelfPermission(
                ExpressFormActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestStoragePermission();
        }

        if (ContextCompat.checkSelfPermission(ExpressFormActivity.this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {

        } else {
            requestCameraPermission();
        }

        if (ContextCompat.checkSelfPermission(
                ExpressFormActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestLocationPermission();
        }


        Bundle extras= getIntent().getExtras();
        vehicleType=extras.getString("vehicleType");

        packageDescription = findViewById(R.id.PackageDescriptionET);
        recipientName = findViewById(R.id.RecipientName);
        recipientPhone = findViewById(R.id.RecipientPhone);
        destination = findViewById(R.id.PackageDestination);
        PackageImage = (ImageView) findViewById(R.id.ExpressPackageImage);
        backToRegular=findViewById(R.id.backRegular);


        backToRegular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToRegular();
            }
        });


        PackageImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(ExpressFormActivity.this);
            }
        });


    }


    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(ExpressFormActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to access the Camera")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(ExpressFormActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("This permission is needed to confirm the location of the photos submitted")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(ExpressFormActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your package image");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        PackageImage.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                PackageImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }


    public void expressOrder(View view) {


        Log.d(TAG, "expressOrder: uploading request");


        String expressPackageDescription = packageDescription.getText().toString();
        String expressRecipientName = recipientName.getText().toString();
        String expressRecipientPhone = recipientPhone.getText().toString();
        String expressRecipientLocation = destination.getText().toString();
        String regularVehicleType=vehicleType;
        Uri expressImage = expressPackageImage;



        if (!(expressPackageDescription.matches("") || expressRecipientName.matches("") || expressImage.equals("") || expressRecipientPhone.matches("") || expressRecipientLocation.matches(""))) {


            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), expressImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i("Photo", "Received");

            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

            byte[] byteArray = stream.toByteArray();

            ParseFile file = new ParseFile("image.png", byteArray);


            ParseObject request = new ParseObject("request");


            request.put("User", ParseUser.getCurrentUser().getUsername());
            request.put("image", file);
            request.put("PackageDescription", expressPackageDescription);
            request.put("RecipientName", expressRecipientName);
            request.put("RecipientPhone", expressRecipientPhone);
            request.put("VehicleType",regularVehicleType);


            request.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.d(TAG, "done: Upload successful");
                    } else {
                        Log.d(TAG, "done: Upload failed");
                        Toast.makeText(ExpressFormActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        } else {
            Toast.makeText(this, "You must fill the whole form", Toast.LENGTH_SHORT).show();
        }


        Intent intent = new Intent(this, ExpressUserMapsActivity.class);
        startActivity(intent);

    }

    public void backToRegular(){

        Intent intent= new Intent(this, ExpressActivity.class);
        startActivity(intent);
    }


}