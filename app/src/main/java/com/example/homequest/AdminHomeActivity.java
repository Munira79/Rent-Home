package com.example.homequest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        // Initialize buttons
        Button btnInsertHome = findViewById(R.id.btn_insert_home);
        Button btnViewHome = findViewById(R.id.btn_view_home);
        ImageButton chatButton = findViewById(R.id.chat_button);
        ImageButton profileButton = findViewById(R.id.profile_button);
        ImageButton setButton = findViewById(R.id.set_button);

        // Navigate to Insert Home Details
        btnInsertHome.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, InsertHomeDetails.class);
            startActivity(intent);
        });

        // Navigate to View Home Details
        btnViewHome.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, ViewHomeDetail.class);
            startActivity(intent);
        });

        // Navigate to Admin Message (Chat)
        chatButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, AdminMessage.class);
            startActivity(intent);
        });
        // Listener for settings button to go to SettingsActivity
        setButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, Settings.class); // Switch to SettingsActivity
            startActivity(intent);
        });

        // Navigate to User Profile
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, UserProfileActivity.class);
            startActivity(intent);
        });
    }
}
