package com.example.homequest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    private ListView listViewUserProfile;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        listViewUserProfile = findViewById(R.id.list_user_profile);
        Button btnDone = findViewById(R.id.btn_done);
        databaseHelper = new DatabaseHelper(this);

        displayUserProfiles();

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDone();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayUserProfiles();
    }

    private void displayUserProfiles() {
        Cursor cursor = databaseHelper.getAllUsers(); // Get all users from the database
        if (cursor != null && cursor.getCount() > 0) {
            UserAdapter adapter = new UserAdapter(this, cursor, 0); // Custom adapter to handle user data
            listViewUserProfile.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No registered users found", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleDone() {
        Intent intent = new Intent(UserProfileActivity.this, AdminHomeActivity.class);
        startActivity(intent);
    }
}
