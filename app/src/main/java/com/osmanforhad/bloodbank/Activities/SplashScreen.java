package com.osmanforhad.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.osmanforhad.bloodbank.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        /**
         * for delay time
         */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
/**
 * for go to next screen
 */
                startActivity(new Intent(SplashScreen.this, RegisterActivity.class));
                finish();
            }
        }, 2500);

    }//end of the onCreate method

}//end of the SplashScreen class