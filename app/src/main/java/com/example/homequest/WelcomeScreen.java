package com.example.homequest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeScreen extends AppCompatActivity {

    ImageView logo;
    TextView appName;
    Animation Splash_top,Splash_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // For full-screen by hiding the status bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_screen);

        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.appName);

        Splash_top = AnimationUtils.loadAnimation(this, R.anim.splash_top);
        Splash_bottom = AnimationUtils.loadAnimation(this, R.anim.splash_bottom);

        logo.setAnimation(Splash_top);
        appName.setAnimation(Splash_bottom);

        // Hide Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Splash Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(WelcomeScreen.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        }, 2000);
    }
}
