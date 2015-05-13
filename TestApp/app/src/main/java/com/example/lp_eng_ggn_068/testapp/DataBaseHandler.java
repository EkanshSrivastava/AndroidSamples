package com.example.lp_eng_ggn_068.testapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "imagedb";

    // Contacts table name
    private static final String TABLE_IMAGE_TABLE = "imageTable";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_IMAGE_DETAILS = "imageDetails";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_DATE = "date";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_IMAGE_TABLE = "CREATE TABLE " + TABLE_IMAGE_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_IMAGE_DETAILS + " TEXT,"
                + KEY_IMAGE + " BLOB," + KEY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_IMAGE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE_TABLE);

    // Create tables again
        onCreate(db);
    }


    public// Adding new record
    void addRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE_DETAILS, record._imageDetails);
        values.put(KEY_IMAGE, record._image);
        values.put(KEY_DATE,record._date);

    // Inserting Row
        db.insert(TABLE_IMAGE_TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Records
    public List<Record> getAllRecords() {
        List<Record> recordsList = new ArrayList<Record>();
    // Select All Query
        String selectQuery = "SELECT * FROM imageTable ORDER BY date DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
    // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setID(Integer.parseInt(cursor.getString(0)));
                record.setImageDetails(cursor.getString(1));
                record.setImage(cursor.getBlob(2));
                record.setDate(cursor.getString(3));
    // Adding record to list
                recordsList.add(record);
            } while (cursor.moveToNext());
        }
    // close inserting data from database
        db.close();
    // return record list
        return recordsList;

    }
}