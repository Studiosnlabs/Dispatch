package com.example.dispatch.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dispatch.ExpressDelivery.ExpressActivity;
import com.example.dispatch.ExpressDelivery.ExpressFormActivity;
import com.example.dispatch.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class UserSignUpActivity extends AppCompatActivity {
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

    public void NextIntent(){
        Intent intent = new Intent(this, ExpressActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        fullname=(EditText) findViewById(R.id.FullName);
        phone=(EditText) findViewById(R.id.Phone);
        TNC_Check=(CheckBox) findViewById(R.id.TnC_Check);
        TNC=(TextView) findViewById(R.id.TnC);
        TextView LoginRedirect=(TextView) findViewById(R.id.loginRedirect);
        username=(EditText) findViewById(R.id.Username);
        password=(EditText) findViewById(R.id.Password);
        eMail=(EditText) findViewById(R.id.Email);
        cPassword=(EditText) findViewById(R.id.CPassword);
        signUp=(Button) findViewById(R.id.signupButton);
        SignUpLogo=(TextView) findViewById(R.id.signUpTV);

        RelativeLayout signupBg = (RelativeLayout) findViewById(R.id.signUpBg);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass1=password.getText().toString();
                String pass2=cPassword.getText().toString();


                Log.i("pass1&pass2",pass1+" & "+pass2);
                if (pass1.equals(pass2) && TNC_Check.isChecked() ){

                    Boolean TnC=true;
                    ParseUser user = new ParseUser();
                    user.setUsername(username.getText().toString());
                    user.setEmail(eMail.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.put("fullname",fullname.getText().toString());
                    user.put("phone",phone.getText().toString());
                    user.put("Tnc",TnC);

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

                    Toast.makeText(UserSignUpActivity.this, "Make sure passwords match and agree to terms and conditions", Toast.LENGTH_SHORT).show();

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

        LoginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ExpressActivity.class);
                startActivity(intent);
            }
        });


    }
}