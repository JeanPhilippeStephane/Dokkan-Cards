package com.dcv.spdesigns.dokkancards.presenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Handles the app's splash screen
 * Implements good design practice : Instead of using a layout file(which would take time to load and waste the user's time)
 * this class uses a theme for the activity's background("instant" creation) thus running only while the other activities &
 * their layout files (etc) are getting created
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
