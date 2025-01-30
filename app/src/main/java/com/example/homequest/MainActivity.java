package com.example.homequest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        EditText etUsername = findViewById(R.id.et_username);
        TextInputEditText etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnRegister = findViewById(R.id.btn_register);

        // Set up Register button
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Set up Login button
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            } else {
                if (username.equals("admin") && password.equals("admin")) {
                    Intent intent = new Intent(MainActivity.this, AdminHomeActivity.class);
                    startActivity(intent);
                } else {
                    DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);
                    boolean result = dbHelper.checkUserByUsername(username, password);

                    if (result) {
                        Toast.makeText(MainActivity.this, "Welcome valid user!!", Toast.LENGTH_SHORT).show();

                        // Save the logged-in username to SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username); // Save username
                        editor.apply();

                        // Navigate to the user's home page or profile
                        Intent intent = new Intent(MainActivity.this, DisplayHome.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Username and password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}