package com.example.travel_geeks.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.travel_geeks.Create_Account;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TravelGeeksDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_RESERVATION = "reservations";
    private static final String TABLE_USER_ACCOUNT = "user_accounts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TICKET_TYPE = "ticket_type";
    private static final String COLUMN_RESERVE_DATE = "reserve_date";
    private static final String COLUMN_TRIP_DATE = "trip_date";
    private static final String COLUMN_DEPARTURE_LOCATION = "departure_location";
    private static final String COLUMN_DESTINATION_LOCATION = "destination_location";
    private static final String COLUMN_TICKET_COUNT = "ticket_count";
    private static final String COLUMN_TICKET_PRICE = "ticket_price";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_NIC = "nic";
    private static final String COLUMN_COUNTRY = "country";
    private static final String COLUMN_MOBILE = "mobile";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RESERVATION_TABLE = "CREATE TABLE " + TABLE_RESERVATION + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TICKET_TYPE + "TEXT,"
                + COLUMN_RESERVE_DATE + " TEXT,"
                + COLUMN_TRIP_DATE + " TEXT,"
                + COLUMN_DEPARTURE_LOCATION + " TEXT,"
                + COLUMN_DESTINATION_LOCATION + " TEXT,"
                + COLUMN_TICKET_COUNT + " TEXT,"
                + COLUMN_TICKET_PRICE + " TEXT" + ")";

        String CREATE_USER_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_USER_ACCOUNT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FIRST_NAME + " TEXT,"
                + COLUMN_LAST_NAME + " TEXT,"
                + COLUMN_NIC + " TEXT,"
                + COLUMN_COUNTRY + " TEXT,"
                + COLUMN_MOBILE + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT" + ")";

        db.execSQL(CREATE_RESERVATION_TABLE);
        db.execSQL(CREATE_USER_ACCOUNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ACCOUNT);
        onCreate(db);
    }

    // Other methods for managing reservations

    public void addUserAccount(String firstName, String lastName, String nic, String country, String mobile, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_LAST_NAME, lastName);
        values.put(COLUMN_NIC, nic);
        values.put(COLUMN_COUNTRY, country);
        values.put(COLUMN_MOBILE, mobile);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        db.insert(TABLE_USER_ACCOUNT, null, values);
        db.close();
    }

    public boolean checkUserCredentials(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USER_ACCOUNT, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }


//    public void updateUserAccount(int id, String firstName, String lastName, String nic, String country, String mobile, String email, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_FIRST_NAME, firstName);
//        values.put(COLUMN_LAST_NAME, lastName);
//        values.put(COLUMN_NIC, nic);
//        values.put(COLUMN_COUNTRY, country);
//        values.put(COLUMN_MOBILE, mobile);
//        values.put(COLUMN_EMAIL, email);
//        values.put(COLUMN_PASSWORD, password);
//
//        db.update(TABLE_USER_ACCOUNT, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
//        db.close();
//    }

    public void deleteUserAccount(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_ACCOUNT, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Create_Account> getAllUserAccounts() {
        String query = "SELECT * FROM " + TABLE_USER_ACCOUNT;
        SQLiteDatabase db = this.getReadableDatabase();
        // Implement logic to return a list of UserAccount objects
        return null;
    }

    public void addReservation(String ticketType, String date, String time, String from, String to, String ticketCount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TICKET_COUNT, ticketType);
        values.put(COLUMN_RESERVE_DATE, date);
        values.put(COLUMN_TRIP_DATE, time);
        values.put(COLUMN_DEPARTURE_LOCATION, from);
        values.put(COLUMN_DESTINATION_LOCATION, to);
        values.put(COLUMN_TICKET_COUNT, ticketCount);

        db.insert(TABLE_RESERVATION, null, values);
        db.close();
    }

    public void updateReservation(int id, String ticketType, String date, String time, String from, String to, String ticketCount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TICKET_COUNT, ticketType);
        values.put(COLUMN_RESERVE_DATE, date);
        values.put(COLUMN_TRIP_DATE, time);
        values.put(COLUMN_DEPARTURE_LOCATION, from);
        values.put(COLUMN_DESTINATION_LOCATION, to);
        values.put(COLUMN_TICKET_COUNT, ticketCount);

        db.update(TABLE_RESERVATION, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteReservation(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESERVATION, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor getAllReservations() {
        String query = "SELECT * FROM " + TABLE_RESERVATION;
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(query, null);
    }
}
