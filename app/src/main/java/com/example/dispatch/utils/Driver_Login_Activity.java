package com.example.dispatch.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dispatch.ExpressDelivery.ExpressFormActivity;
import com.example.dispatch.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Driver_Login_Activity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;

    public void NextIntent(){
        Intent intent = new Intent(this, ExpressFormActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__login_);

        username =(EditText) findViewById(R.id.UsernameET);
        password =(EditText) findViewById(R.id.PasswordET);
        login=(Button) findViewById(R.id.loginButton);
        TextView loginTV=(TextView) findViewById(R.id.LoginTV);
        TextView loginRedirect=(TextView) findViewById(R.id.loginRedirect);
        RelativeLayout loginBg=(RelativeLayout) findViewById(R.id.LoginBg);



        if (ParseUser.getCurrentUser() != null){
            NextIntent();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserNameIN = username.getText().toString();
                String PassWordIN = password.getText().toString();
                ParseUser.logInInBackground(UserNameIN, PassWordIN, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user!=null && e==null){
                            Log.i("Login","Successful");
                            NextIntent();
                        }else {
                            Log.i("Login ","Failed " +e.toString());
                            Toast.makeText(Driver_Login_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i==KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){

                    String UserNameIN = username.getText().toString();
                    String PassWordIN = password.getText().toString();
                    ParseUser.logInInBackground(UserNameIN, PassWordIN, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user!=null && e==null){
                                Log.i("Login","Successful");
                                NextIntent();
                            }else {
                                Log.i("Login ","Failed " +e.toString());
                                Toast.makeText(Driver_Login_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }
                return false;
            }
        });


        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),DriverSignUpActivity.class);
                startActivity(intent);


            }
        });

        loginBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            }
        });


        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

            }
        });
    }
}