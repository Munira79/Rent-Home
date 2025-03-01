package com.example.homequest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView usernameTextView, emailTextView, mobileTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // Set your profile layout

        // Initialize the TextViews
        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        mobileTextView = findViewById(R.id.mobileTextView);
        Button btnLogout = findViewById(R.id.btn_Logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogout();
            }
        });

        // Fetch the logged-in username from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        String loggedInUsername = sharedPreferences.getString("username", "");

        if (!loggedInUsername.isEmpty()) {
            // Fetch the user profile using the DatabaseHelper
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            Cursor cursor = dbHelper.getUserByUsername(loggedInUsername);

            if (cursor != null && cursor.moveToFirst()) {
                // Extract user details from cursor
                String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USERNAME));
                String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMAIL));
                String mobile = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MOBILE));

                // Set these values in the TextViews
                usernameTextView.setText(username);
                emailTextView.setText(email);
                mobileTextView.setText(mobile);

                cursor.close(); // Close the cursor
            } else {
                // Handle case when user data is not found
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle the case where no user is logged in
            Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleLogout() {
        // Clear the SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Navigate to the MainActivity
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear activity stack
        startActivity(intent);
        finish(); // Finish this activity
    }
}
