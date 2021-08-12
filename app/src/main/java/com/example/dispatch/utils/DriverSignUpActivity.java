package com.example.dispatch.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dispatch.ExpressDelivery.ExpressActivity;
import com.example.dispatch.R;
import com.example.dispatch.RegularDelivery.RegularDriversActivity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class DriverSignUpActivity extends AppCompatActivity {

    EditText fullname;
    EditText phone;
    CheckBox TNC_Check;
    TextView TNC;
    EditText username;
    EditText password;
    EditText eMail;
    EditText cPassword;
    Button signUp;
    TextView SignUpLogo;
    EditText LicenceID;
    EditText VehicleModule;
    EditText LicencePlate;
    Spinner dropdown;
    RadioButton Yes;

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Boolean getDriver() {
        return isDriver;
    }

    public void setDriver(Boolean driver) {
        isDriver = driver;
    }

    String Gender;
    Boolean isDriver;


    public void NextIntent(){
        Intent intent = new Intent(this, RegularDriversActivity.class);
        startActivity(intent);
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_male:
                if (checked)
                {
                    setGender("Male");
                }
                    break;
            case R.id.radio_female:
                if (checked)
                {
                    setGender("Female");
                }
                    break;
            case R.id.radio_Yes:
                if (checked)
                {
                    setDriver(true);
                }
                    break;
            case R.id.radio_No:
                if (checked)
                {
                    setDriver(false);
                    Toast.makeText(this, "You cant register without a valid drivers licence", Toast.LENGTH_SHORT).show();
                }
                    break;

        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_sign_up);



        fullname=(EditText) findViewById(R.id.Drive_FullName);
        phone=(EditText) findViewById(R.id.Drive_Phone);
        TNC_Check=(CheckBox) findViewById(R.id.TnC_Check);
        TNC=(TextView) findViewById(R.id.TnC);
        TextView LoginRedirect=(TextView) findViewById(R.id.loginRedirect);
        username=(EditText) findViewById(R.id.Drive_Username);
        password=(EditText) findViewById(R.id.Drive_Password);
        eMail=(EditText) findViewById(R.id.Drive_Email);
        cPassword=(EditText) findViewById(R.id.Drive_CPassword);
        signUp=(Button) findViewById(R.id.Drive_signupButton);
        SignUpLogo=(TextView) findViewById(R.id.signUpTV);
        LicenceID =(EditText) findViewById(R.id.LicenceID);
        VehicleModule=(EditText) findViewById(R.id.VehicleModule);
        LicencePlate=(EditText) findViewById(R.id.licencePlateNumber);
        dropdown = findViewById(R.id.Drive_vehicleType);
        Yes=findViewById(R.id.radio_Yes);

        RelativeLayout signupBg = (RelativeLayout) findViewById(R.id.signUpBg);
        ScrollView scrollView = findViewById(R.id.signupScroll);
        RelativeLayout signupHeader = (RelativeLayout) findViewById(R.id.SignUpHeader);


        String[] items = new String[]{"Select a type of vehicle","Bicycle", "Motor bike", "Small Car","Caravan","Pick up Truck"};

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







        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass1=password.getText().toString();
                String pass2=cPassword.getText().toString();
                String vehicleType=dropdown.getSelectedItem().toString();
                Boolean driver = getDriver();
                String Sex=getGender();
                String fullName=fullname.getText().toString();
                String email=eMail.getText().toString();
                String Licence= LicenceID.getText().toString();
                String LicencePlateNum=LicencePlate.getText().toString();
                String phoneNum= phone.getText().toString();



                Log.i("pass1&pass2",pass1+" & "+pass2);
                if (pass1.equals(pass2) && TNC_Check.isChecked() && Yes.isChecked() && !(fullName.matches("") || email.matches("") || Sex.matches("") || vehicleType.matches("") || pass1.matches("") || Licence.matches("")  || LicencePlateNum.matches("")  || phoneNum.matches(""))){

                    Boolean TnC=true;
                    ParseUser user = new ParseUser();
                    user.setUsername(username.getText().toString());
                    user.setEmail(eMail.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.put("fullName",fullname.getText().toString());
                    user.put("phone",phone.getText().toString());
                    user.put("Tnc",TnC);
                    user.put("isDriver",driver);
                    user.put("VehicleType",vehicleType);
                    user.put("Gender",Sex);
                    user.put("LicencePlate",LicencePlate.getText().toString());




                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e==null){
                                Log.i("SignUp: ","Successful");
                                NextIntent();

                            }else{
                                Log.i("SignUp","failed "+e.toString());
                            }
                        }
                    });


                } else {

                    Toast.makeText(DriverSignUpActivity.this, "Make sure passwords match and agree to terms and conditions", Toast.LENGTH_SHORT).show();

                }


            }
        });


        signupBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            }
        });


        signupHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            }
        });


        LoginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }






}