package com.example.homequest;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AdminMessage extends AppCompatActivity {

    private ListView messageListView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_message);

        messageListView = findViewById(R.id.messageListView);
        dbHelper = new DatabaseHelper(this);

        loadMessages();
    }

    private void loadMessages() {
        List<String> messageList = new ArrayList<>();

        // Fetch messages from the database
        Cursor cursor = dbHelper.getAllUserMessages();

        if (cursor.moveToFirst()) {
            do {
                String message = cursor.getString(cursor.getColumnIndexOrThrow("message"));
                messageList.add(message);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Show the messages in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messageList);
        messageListView.setAdapter(adapter);
    }
}
