package com.example.homequest;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

public class UserAdapter extends CursorAdapter {

    public UserAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate the item layout for the list view
        return LayoutInflater.from(context).inflate(R.layout.user_profile_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Bind data to the views in the layout
        TextView tvUsername = view.findViewById(R.id.tv_username);
        TextView tvEmail = view.findViewById(R.id.tv_email);
        TextView tvMobile = view.findViewById(R.id.tv_mobile);

        // Get data from the cursor
        String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USERNAME));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_EMAIL));
        String mobile = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MOBILE));

        // Set the data to the views
        tvUsername.setText("Username: " + username);
        tvEmail.setText("Email: " + email);
        tvMobile.setText("Mobile: " + mobile);
    }
}
