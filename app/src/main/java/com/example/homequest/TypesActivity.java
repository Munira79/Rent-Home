package com.example.homequest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TypesActivity extends AppCompatActivity {

    private RadioGroup typeRadioGroup;
    private SeekBar budgetSeekBar;
    private TextView tvSeekbarValue;
    private Button nextButton;

    // Define the budget range
    private final int MIN_BUDGET = 5000;
    private final int MAX_BUDGET = 20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types); // Ensure correct layout is used

        // Initialize the views
        typeRadioGroup = findViewById(R.id.typeRadioGroup);
        budgetSeekBar = findViewById(R.id.budgetSeekBar);
        tvSeekbarValue = findViewById(R.id.tv_seekbar_value);
        nextButton = findViewById(R.id.nextButton); // Ensure the ID matches the XML

        // Initialize SeekBar with a listener to update the displayed budget
        budgetSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int calculatedBudget = MIN_BUDGET + progress;
                tvSeekbarValue.setText("Selected budget: " + calculatedBudget);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Optional: Add behavior for when user starts interacting with the SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Optional: Add behavior for when user stops interacting with the SeekBar
            }
        });

        // Set up the Next button click listener
        nextButton.setOnClickListener(view -> {
            // Get selected category from RadioGroup
            int selectedRadioButtonId = typeRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

            // Calculate the budget based on SeekBar progress
            int selectedBudget = MIN_BUDGET + budgetSeekBar.getProgress();

            if (selectedRadioButton != null) {
                // Display selected category and budget as a toast
                String selectedCategory = selectedRadioButton.getText().toString();
                Toast.makeText(TypesActivity.this,
                        "Category: " + selectedCategory + "\nBudget: " + selectedBudget,
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TypesActivity.this, "Please select a category", Toast.LENGTH_SHORT).show();
            }

            // Navigate to SearchActivity
            Intent intent = new Intent(TypesActivity.this, SearchActivity.class);
            startActivity(intent);
        });
    }
}
