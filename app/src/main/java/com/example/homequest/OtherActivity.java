package com.example.homequest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OtherActivity extends AppCompatActivity {

    private RadioGroup preferenceRadioGroup;
    private RatingBar ratingBar;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other); // Ensure the correct layout is used

        // Initialize the views
        preferenceRadioGroup = findViewById(R.id.preferenceRadioGroup);
        ratingBar = findViewById(R.id.ratingBar);
        submitButton = findViewById(R.id.submitButton);

        // Set up the Submit button click listener
        submitButton.setOnClickListener(view -> {
            // Get the selected RadioButton ID from RadioGroup
            int selectedRadioButtonId = preferenceRadioGroup.getCheckedRadioButtonId();

            // Ensure a selection is made
            if (selectedRadioButtonId == -1) {
                Toast.makeText(OtherActivity.this, "Please select a preference", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get the text of the selected RadioButton
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            String selectedPreference = selectedRadioButton.getText().toString();

            // Get the rating from RatingBar
            float rating = ratingBar.getRating();

            // Display the selected preference and rating as a toast
            Toast.makeText(OtherActivity.this,
                    "Preference: " + selectedPreference + "\nRating: " + rating,
                    Toast.LENGTH_SHORT).show();

            // Navigate to SearchActivity
            Intent intent = new Intent(OtherActivity.this, SearchActivity.class);
            startActivity(intent);
        });
    }
}
