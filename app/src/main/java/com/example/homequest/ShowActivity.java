package com.example.homequest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView nameTextView = findViewById(R.id.detail_name);
        TextView contactTextView = findViewById(R.id.detail_contact);
        ImageView imageView = findViewById(R.id.detail_image);
        ImageButton chatButton = findViewById(R.id.chat_button);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String contact = intent.getStringExtra("contact");
        int imageResId = intent.getIntExtra("imageResId", 0);

        nameTextView.setText(name);
        contactTextView.setText(contact);
        imageView.setImageResource(imageResId);

        // Listener for chat button to go to the MessageActivity
        chatButton.setOnClickListener(v -> {
            Intent intents = new Intent(ShowActivity.this, Message.class); // Switch to MessageActivity
            startActivity(intents);
        });

    }
}
