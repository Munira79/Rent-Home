package com.example.homequest;

import android.content.Intent;
import android.media.FaceDetector;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FavouriteActivity extends AppCompatActivity {


        private ImageButton btnBack, btnClear;
        private ListView listViewHomeDetail;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_favourite);  // Assuming this XML is activity_register.xml

            // Initialize views
            btnBack = findViewById(R.id.btn_back);  // Assign a proper id for the ImageButton
            btnClear = findViewById(R.id.btn_clear);  // Assign a proper id for the ImageButton
            listViewHomeDetail = findViewById(R.id.list_view_home_detail);

            // Back button click listener
            btnBack.setOnClickListener(v -> {
                // Navigate to DisplayHomeActivity
                Intent intent = new Intent(FavouriteActivity.this, DisplayHome.class);
                startActivity(intent);
                finish();  // Close current activity
            });

            // Clear button click listener
            btnClear.setOnClickListener(v -> {
                // Clear the ListView or any other inputs you need to reset
                clearForm();
                Toast.makeText(FavouriteActivity.this, "Data Cleared", Toast.LENGTH_SHORT).show();

                // Navigate to DoneActivity
                Intent intent = new Intent(FavouriteActivity.this, OtherActivity.class);
                startActivity(intent);
                finish();  // Close current activity
            });
        }

        // Clear form or list logic (based on your requirement)
        private void clearForm() {
            // Clear ListView data if necessary, or reset any forms.
            // Assuming it's resetting the ListView data, otherwise you can reset forms.
            listViewHomeDetail.setAdapter(null);  // This clears the ListView
            // Reset other fields or form data if required.
        }
    }
