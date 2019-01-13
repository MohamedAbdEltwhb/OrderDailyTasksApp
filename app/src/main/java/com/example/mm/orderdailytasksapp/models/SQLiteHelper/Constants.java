package com.example.mm.orderdailytasksapp.models.SQLiteHelper;

public class Constants {

    public static final String TABLE_NAME = "tasks";

    public static final String C_ID = "_id";
    public static final String TITLE = "title";
    public static final String DETAIL = "description";
    public static final String TIME = "time";
    public static final String DATE = "date";

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITLE + " TEXT, "
            + DETAIL + " TEXT, "
            + TIME + " TEXT, "
            + DATE + " TEXT)";

    public static String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
