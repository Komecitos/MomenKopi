package com.example.momen_kopi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "momen_kopi.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ORDER = "orders";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_ORDERED_ITEMS = "ordered_items";
    public static final String COLUMN_ORDERED_TEMPERATURES = "ordered_temperatures";
    public static final String COLUMN_ORDERED_QUANTITIES = "ordered_quantities";
    public static final String COLUMN_ORDERED_PRICES = "ordered_prices";
    public static final String COLUMN_TOTAL_PRICE = "total_price";
    public static final String COLUMN_PAYMENT_METHOD = "payment_method";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel pemesanan
        String CREATE_TABLE_ORDERS = "CREATE TABLE " + TABLE_ORDER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PHONE_NUMBER + " TEXT,"
                + COLUMN_ORDERED_ITEMS + " TEXT,"
                + COLUMN_ORDERED_TEMPERATURES + " TEXT,"
                + COLUMN_ORDERED_QUANTITIES + " TEXT,"
                + COLUMN_ORDERED_PRICES + " TEXT,"
                + COLUMN_TOTAL_PRICE + " INTEGER,"
                + COLUMN_PAYMENT_METHOD + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE_ORDERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Menangani upgrade database
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        onCreate(db);
    }
}
