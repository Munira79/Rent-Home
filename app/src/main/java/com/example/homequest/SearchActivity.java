package com.example.homequest;

import android.content.Intent; // Add this import
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchActivity extends AppCompatActivity {

    private EditText etSearch;
    private ImageView searchButton;
    private ImageButton settingButton, homeButton, userButton;
    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private List<HomeS> homeList, filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch = findViewById(R.id.et_search);
        searchButton = findViewById(R.id.search_button);
        recyclerView = findViewById(R.id.recycler_view);
        settingButton = findViewById(R.id.setting_button);
        homeButton = findViewById(R.id.home_button);
        userButton = findViewById(R.id.user_button);


        homeList = new ArrayList<>();
        filteredList = new ArrayList<>();

        // Dummy data
        homeList.add(new HomeS("Sanjida Vila", "12345678", R.drawable.img_1));
        homeList.add(new HomeS("Munira Manjil", "01234567", R.drawable.img_3));
        homeList.add(new HomeS("Zuarder house", "3678823", R.drawable.img_2));
        homeList.add(new HomeS("Dream House", "895667", R.drawable.img_4));
        homeList.add(new HomeS("ABC House", "2398892", R.drawable.img_10));

        adapter = new SearchAdapter(this, filteredList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(v -> {
            String searchText = etSearch.getText().toString().trim();
            if (!searchText.isEmpty()) {
                searchHome(searchText);
            } else {
                Toast.makeText(SearchActivity.this, "Please enter a home name to search", Toast.LENGTH_SHORT).show();
            }
        });
        // Listener for settings button to go to SettingsActivity
        settingButton.setOnClickListener(v -> {
            Intent intent = new Intent(SearchActivity.this, Settings.class); // Switch to SettingsActivity
            startActivity(intent);
        });
// Listener for home button
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(SearchActivity.this, DisplayHome.class);
            startActivity(intent);
        });

        // Listener for profile 2 button
        userButton.setOnClickListener(v -> {
            Intent intent = new Intent(SearchActivity.this, ProfileActivity.class);
            startActivity(intent);

        });

    }

    private void searchHome(String searchText) {
        filteredList.clear();
        for (HomeS home : homeList) {
            if (home.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(home);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No homes found", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }
}
