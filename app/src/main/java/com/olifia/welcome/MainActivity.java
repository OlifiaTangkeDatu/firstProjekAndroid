package com.olifia.welcome;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static int Splash_Time_Out = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
            Intent homeInten = new Intent(MainActivity.this, navigation.class);
                startActivity(homeInten);
                finish();
        }
        },Splash_Time_Out);
    }


}
