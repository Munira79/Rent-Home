
package com.example.homequest;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

    public class Settings extends AppCompatActivity {

        Switch notificationSwitch, locationSwitch;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);

            // Initialize switches
            notificationSwitch = findViewById(R.id.notificationSwitch);
            locationSwitch = findViewById(R.id.soundSwitch);

            // Set up listeners for each switch
            notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    Toast.makeText(Settings.this, "Notifications Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Settings.this, "Notifications Disabled", Toast.LENGTH_SHORT).show();
                }
            });

            locationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    Toast.makeText(Settings.this, "Location Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Settings.this, "Location Disabled", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
