package com.example.homequest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OtherActivity extends AppCompatActivity {

    private CheckBox firstCheckBox, secondCheckBox, thirdCheckBox;
    private RatingBar ratingBar;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other); // Ensure the correct layout is used

        // Initialize the views
        firstCheckBox = findViewById(R.id.first_check_box);
        secondCheckBox = findViewById(R.id.second_check_box);
        thirdCheckBox = findViewById(R.id.third_check_box);
        ratingBar = findViewById(R.id.ratingBar);
        submitButton = findViewById(R.id.submitButton);

        // Set up the Submit button click listener
        submitButton.setOnClickListener(view -> {
            // Get the CheckBox selections
            StringBuilder preferences = new StringBuilder("Preference: ");
            if (firstCheckBox.isChecked()) {
                preferences.append("Bad ");
            }
            if (secondCheckBox.isChecked()) {
                preferences.append("Good ");
            }
            if (thirdCheckBox.isChecked()) {
                preferences.append("Best ");
            }

            // Get the rating from RatingBar
            float rating = ratingBar.getRating();

            // Display the selected preferences and rating as a toast
            Toast.makeText(OtherActivity.this,
                    preferences + "\nRating: " + rating,
                    Toast.LENGTH_SHORT).show();
        });
    }
}
