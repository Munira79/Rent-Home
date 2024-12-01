package com.example.homequest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types); // Ensure correct layout is used

        // Initialize the views
        typeRadioGroup = findViewById(R.id.typeRadioGroup);
        budgetSeekBar = findViewById(R.id.budgetSeekBar);
        tvSeekbarValue = findViewById(R.id.tv_seekbar_value);
        nextButton = findViewById(R.id.nextButton); // Ensure the ID matches the XML

        // Set up SeekBar listener to update the selected budget value
        budgetSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekbarValue.setText("Selected budget: " + progress);
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

            // Get the selected budget value
            int selectedBudget = budgetSeekBar.getProgress();

            if (selectedRadioButton != null) {
                // Display selected category and budget as a toast
                String selectedCategory = selectedRadioButton.getText().toString();
                Toast.makeText(TypesActivity.this, // Correct context
                        "Category: " + selectedCategory + "\nBudget: " + selectedBudget,
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TypesActivity.this, "Please select a category", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(TypesActivity.this, SearchActivity.class);
            startActivity(intent);
        });
    }
}
