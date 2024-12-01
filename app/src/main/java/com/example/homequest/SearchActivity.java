package com.example.homequest;

import android.content.Intent; // Add this import
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText etSearch;
    private ImageView searchButton;
    private ImageButton chatButton; // Declare the chat button
    private ImageButton settingButton; // Declare the settings button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch = findViewById(R.id.et_search);
        searchButton = findViewById(R.id.search_button);
        chatButton = findViewById(R.id.chat_button); // Initialize the chat button
        settingButton = findViewById(R.id.setting_button); // Initialize the settings button

        // Listener for search button
        searchButton.setOnClickListener(v -> {
            String searchText = etSearch.getText().toString().trim();
            if (!searchText.isEmpty()) {
                searchHome(searchText);
            } else {
                Toast.makeText(SearchActivity.this, "Please enter a home name to search", Toast.LENGTH_SHORT).show();
            }
        });

        // Listener for chat button to go to the MessageActivity
        chatButton.setOnClickListener(v -> {
            Intent intent = new Intent(SearchActivity.this, Message.class); // Switch to MessageActivity
            startActivity(intent);
        });

        // Listener for settings button to go to SettingsActivity
        settingButton.setOnClickListener(v -> {
            Intent intent = new Intent(SearchActivity.this, Settings.class); // Switch to SettingsActivity
            startActivity(intent);
        });
    }

    private void searchHome(String searchText) {
        // Dummy data for demonstration
        List<String> homeList = new ArrayList<>();
        homeList.add("Sanjida Vila");
        homeList.add("Munira Manjil");
        homeList.add("Zuarder house");
        homeList.add("Dream House");

        List<String> searchResults = new ArrayList<>();
        for (String home : homeList) {
            if (home.toLowerCase().contains(searchText.toLowerCase())) {
                searchResults.add(home);
            }
        }

        if (!searchResults.isEmpty()) {
            // Show search results (for simplicity, we'll just show a Toast)
            String results = "Found: " + searchResults.toString();
            Toast.makeText(SearchActivity.this, results, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(SearchActivity.this, "No homes found", Toast.LENGTH_SHORT).show();
        }
    }
}
