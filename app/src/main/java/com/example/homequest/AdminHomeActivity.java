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

        Button btnInsertHome = findViewById(R.id.btn_insert_home);
        Button btnViewHome = findViewById(R.id.btn_view_home);
        ImageButton chatButton = findViewById(R.id.chat_button); // Add this line

        btnInsertHome.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, InsertHomeDetails.class);
            startActivity(intent);
        });

        btnViewHome.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, ViewHomeDetail.class);
            startActivity(intent);
        });

        // Set an OnClickListener for the chat button
        chatButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, AdminMessage.class);
            startActivity(intent);
        });
    }
}
