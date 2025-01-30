package com.example.homequest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HomeQuest_DB";
    public static final int DATABASE_VERSION = 8; // Incremented version

    // Table and Column names for Register table
    private static final String TABLE_REGISTER = "register";
    public static final String COL_ID = "_id";
    public static final String COL_USERNAME = "username";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";
    public static final String COL_MOBILE = "mobile";

    // Table and Column names for Homes table
    private static final String TABLE_HOMES = "homes";
    public static final String COL_HOUSE_NAME = "house_name";
    public static final String COL_LOCATION = "location";
    public static final String COL_TOTAL_ROOM = "total_room";
    public static final String COL_RENT_AMOUNT = "rent_amount";
    public static final String COL_HOUSE_IMAGE_URI = "house_image_uri";

    // Table and Column names for Messages table
    private static final String TABLE_MESSAGES = "messages"; // Corrected table name
    public static final String COL_MESSAGE_ID = "message_id"; // Column for message ID
    public static final String COL_MESSAGE_TEXT = "message"; // Column for message text
    public static final String COL_SENDER = "sender"; // Column for sender's identifier

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Register table
        db.execSQL("CREATE TABLE " + TABLE_REGISTER + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT UNIQUE, " +  // Ensure username is unique
                COL_EMAIL + " TEXT UNIQUE, " +  // Ensure email is unique
                COL_PASSWORD + " TEXT, " +
                COL_MOBILE + " TEXT)");

        // Create Homes table
        db.execSQL("CREATE TABLE " + TABLE_HOMES + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_HOUSE_NAME + " TEXT, " +
                COL_LOCATION + " TEXT, " +
                COL_TOTAL_ROOM + " INTEGER, " +
                COL_RENT_AMOUNT + " REAL, " +
                COL_HOUSE_IMAGE_URI + " BLOB)");

        // Create Messages table
        db.execSQL("CREATE TABLE " + TABLE_MESSAGES + " (" +
                COL_MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_MESSAGE_TEXT + " TEXT, " +
                COL_SENDER + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOMES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        onCreate(db);
    }

    // Method to insert a user
    public boolean insertUser(String username, String email, String password, String mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, username);
        values.put(COL_EMAIL, email);
        values.put(COL_PASSWORD, password);
        values.put(COL_MOBILE, mobile);

        long result = db.insert(TABLE_REGISTER, null, values);
        return result != -1;  // Return true if insertion is successful
    }

    // Method to check if a user exists with the given username and password
    public boolean checkUserByUsername(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_REGISTER + " WHERE " + COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean userExists = cursor.getCount() > 0; // Check if any records exist
        cursor.close(); // Don't forget to close the cursor
        return userExists; // Return true if a user exists with the given credentials
    }

    // Method to retrieve all registered users
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_REGISTER, null);
    }


    // Method to insert home details
    public boolean insertHome(String houseName, String location, int totalRoom, double rentAmount, byte[] houseImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_HOUSE_NAME, houseName);
        values.put(COL_LOCATION, location);
        values.put(COL_TOTAL_ROOM, totalRoom);
        values.put(COL_RENT_AMOUNT, rentAmount);
        values.put(COL_HOUSE_IMAGE_URI, houseImage);

        long result = db.insert(TABLE_HOMES, null, values);
        return result != -1;  // Return true if insertion is successful
    }

    // Method to retrieve all homes
    public Cursor getAllHomes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_HOMES, null);
    }

    // Method to retrieve home details by house name
    public Cursor getHomeByName(String houseName) {
        houseName = houseName.trim(); // Ensure the input is trimmed
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DatabaseHelper", "Searching for house: " + houseName); // Log for debugging
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_HOMES + " WHERE " + COL_HOUSE_NAME + " = ?", new String[]{houseName});
        if (cursor != null && cursor.getCount() > 0) {
            Log.d("DatabaseHelper", "House found: " + houseName); // Log for debugging
        } else {
            Log.d("DatabaseHelper", "House not found: " + houseName); // Log for debugging
        }
        return cursor;
    }


    // Method to delete home by house name
    public int deleteHome(String houseName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_HOMES, COL_HOUSE_NAME + " = ?", new String[]{houseName});
    }

    // Update home details
    public boolean updateHome(int houseId, String houseName, String location, int totalRoom, double rentAmount, byte[] houseImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_HOUSE_NAME, houseName);
        values.put(COL_LOCATION, location);
        values.put(COL_TOTAL_ROOM, totalRoom);
        values.put(COL_RENT_AMOUNT, rentAmount);
        values.put(COL_HOUSE_IMAGE_URI, houseImage);

        int rowsAffected = db.update(TABLE_HOMES, values, COL_ID + " = ?", new String[]{String.valueOf(houseId)});
        return rowsAffected > 0;
    }
    // New method to insert a message
    public boolean insertMessage(String message, String sender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_MESSAGE_TEXT, message);
        values.put(COL_SENDER, sender);

        long result = db.insert(TABLE_MESSAGES, null, values);
        return result != -1; // Return true if insertion is successful
    }

    // New method to retrieve all messages
    public Cursor getAllUserMessages() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_MESSAGES, null);
    }

    // Method to retrieve a user by username(24 january)
    public Cursor getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_REGISTER + " WHERE " + COL_USERNAME + " = ?";
        return db.rawQuery(query, new String[]{username});
    }


    public boolean isEmailUnique(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REGISTER + " WHERE " + COL_EMAIL + " = ?", new String[]{email});
        boolean isUnique = !cursor.moveToFirst(); // If no records exist, email is unique
        cursor.close();
        return isUnique;
    }

    public boolean isPhoneUnique(String mobile) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REGISTER + " WHERE " + COL_MOBILE + " = ?", new String[]{mobile});
        boolean isUnique = !cursor.moveToFirst(); // If no records exist, phone is unique
        cursor.close();
        return isUnique;
    }

}