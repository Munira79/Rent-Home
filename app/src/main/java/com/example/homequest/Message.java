package com.example.homequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Message extends AppCompatActivity {

    private EditText messageInput;
    private Button sendMessageButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        messageInput = findViewById(R.id.messageInput);
        sendMessageButton = findViewById(R.id.sendMessageButton);
        dbHelper = new DatabaseHelper(this); // Initialize the database helper

        sendMessageButton.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String message = messageInput.getText().toString().trim();

        if (!message.isEmpty()) {
            // Save the message in the database with sender as 'user'
            boolean result = dbHelper.insertMessage(message, "user");

            if (result) {
                Toast.makeText(Message.this, "Message sent", Toast.LENGTH_SHORT).show();
                messageInput.setText(""); // Clear input field after sending the message
            } else {
                Toast.makeText(Message.this, "Failed to send message", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
        }
    }
}
