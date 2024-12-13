package com.example.momen_kopi;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Buka database untuk operasi
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Tutup database
    public void close() {
        dbHelper.close();
    }

    // Masukkan data pemesanan
    public long insertOrder(String phoneNumber, String orderedItems, String orderedTemperatures,
                            String orderedQuantities, String orderedPrices, int totalPrice, String paymentMethod) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_PHONE_NUMBER, phoneNumber);
        values.put(DBHelper.COLUMN_ORDERED_ITEMS, orderedItems);
        values.put(DBHelper.COLUMN_ORDERED_TEMPERATURES, orderedTemperatures);
        values.put(DBHelper.COLUMN_ORDERED_QUANTITIES, orderedQuantities);
        values.put(DBHelper.COLUMN_ORDERED_PRICES, orderedPrices);
        values.put(DBHelper.COLUMN_TOTAL_PRICE, totalPrice);
        values.put(DBHelper.COLUMN_PAYMENT_METHOD, paymentMethod);

        return database.insert(DBHelper.TABLE_ORDER, null, values);
    }
}
