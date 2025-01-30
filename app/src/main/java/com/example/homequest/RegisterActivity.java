package com.example.homequest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etConfirmPassword, etMobile;
    private Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);  // Make sure activity_register.xml exists

        // Initialize views
        etUsername = findViewById(R.id.et_register_username);
        etEmail = findViewById(R.id.et_register_email);
        etPassword = findViewById(R.id.et_register_password);
        etConfirmPassword = findViewById(R.id.et_register_confirm_password);
        etMobile = findViewById(R.id.et_register_mobile);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);

        // Set click listener for the register button
        btnRegister.setOnClickListener(v -> {
            if (validateInputs()) {
                // Registration successful, go to DisplayHomeActivity
                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();  // Close the RegisterActivity so user can't go back to it
            }
        });


        // Set click listener for the login button
        btnLogin.setOnClickListener(v -> {
            // Redirect to MainActivity
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();  // Close RegisterActivity after redirection
        });
    }

    private boolean validateInputs() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();

        DatabaseHelper dbHelper = new DatabaseHelper(RegisterActivity.this);

        // Validate Username
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return false;
        }

        // Validate Email
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email address");
            etEmail.requestFocus();
            return false;
        } else if (!dbHelper.isEmailUnique(email)) {
            etEmail.setError("This email is already registered! Please use a different email.");
            etEmail.requestFocus();
            return false;
        }

        // Validate Password
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return false;
        } else if (password.length() < 6) {
            etPassword.setError("Password should be at least 6 characters");
            etPassword.requestFocus();
            return false;
        } else if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            etPassword.setError("Password must contain at least one uppercase letter");
            etPassword.requestFocus();
            return false;
        } else if (!Pattern.compile("[a-z]").matcher(password).find()) {
            etPassword.setError("Password must contain at least one lowercase letter");
            etPassword.requestFocus();
            return false;
        } else if (!Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find()) {
            etPassword.setError("Password must contain at least one special character (e.g., !@#$%^&*)");
            etPassword.requestFocus();
            return false;
        }

        // Validate Confirm Password
        if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.setError("Please confirm your password");
            etConfirmPassword.requestFocus();
            return false;
        } else if (!confirmPassword.equals(password)) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return false;
        }

        // Validate Mobile Number
        if (TextUtils.isEmpty(mobile)) {
            etMobile.setError("Mobile number is required");
            etMobile.requestFocus();
            return false;
        } else if (!Pattern.compile("^01[0-9]{9}$").matcher(mobile).matches()) {
            etMobile.setError("Mobile number must be 11 digits and start with '01'");
            etMobile.requestFocus();
            return false;
        } else if (!dbHelper.isPhoneUnique(mobile)) {
            etMobile.setError("This mobile number is already registered! Please use a different mobile number.");
            etMobile.requestFocus();
            return false;
        }

        // If all validations pass, insert the user
        boolean isInserted = dbHelper.insertUser(username, email, password, mobile);
        if (!isInserted) {
            Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
            return false;
        }

        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
        return true;
    }
}