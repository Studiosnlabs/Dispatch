package com.example.dispatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.dispatch.BatchDelivery.BatchActivity;
import com.example.dispatch.ExpressDelivery.ExpressActivity;
import com.example.dispatch.utils.Driver_Login_Activity;
import com.example.dispatch.utils.LoginActivity;

public class SplashActivity extends AppCompatActivity {


    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, BatchActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);

    }





    }
