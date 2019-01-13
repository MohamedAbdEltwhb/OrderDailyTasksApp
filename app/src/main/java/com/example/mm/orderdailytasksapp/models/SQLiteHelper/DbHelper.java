package com.example.mm.orderdailytasksapp.models.SQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.mm.orderdailytasksapp.models.SQLiteHelper.Constants.CREATE_TABLE;
import static com.example.mm.orderdailytasksapp.models.SQLiteHelper.Constants.C_ID;
import static com.example.mm.orderdailytasksapp.models.SQLiteHelper.Constants.DATE;
import static com.example.mm.orderdailytasksapp.models.SQLiteHelper.Constants.DETAIL;
import static com.example.mm.orderdailytasksapp.models.SQLiteHelper.Constants.DROP_TABLE;
import static com.example.mm.orderdailytasksapp.models.SQLiteHelper.Constants.TABLE_NAME;
import static com.example.mm.orderdailytasksapp.models.SQLiteHelper.Constants.TIME;
import static com.example.mm.orderdailytasksapp.models.SQLiteHelper.Constants.TITLE;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "remind";
    public static final int VERSION = 2;

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
    }

    public long addNewTask(String title, String description, String timeString, String dateString) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(TITLE, title);
        cv.put(DETAIL, description);
        cv.put(TIME, timeString);
        cv.put(DATE, dateString);

        return sqLiteDatabase.insert(TABLE_NAME, null, cv);
    }

    public Cursor getAllGuests() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                C_ID
        );
    }
}
