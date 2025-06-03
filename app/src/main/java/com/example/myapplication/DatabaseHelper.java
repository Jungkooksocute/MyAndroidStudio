package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "exchange_rates.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_RATES = "rates";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CURRENCY = "currency";
    public static final String COLUMN_RATE = "rate";
    public static final String COLUMN_LAST_UPDATE = "last_update";

    public DatabaseHelper(UpdatedDaily context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_RATES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CURRENCY + " TEXT UNIQUE, " +
                COLUMN_RATE + " REAL, " +
                COLUMN_LAST_UPDATE + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RATES);
        onCreate(db);
    }

    public void saveRates(List<ExchangeRate> rates) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (ExchangeRate rate : rates) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_CURRENCY, rate.getCurrencyName());
                values.put(COLUMN_RATE, rate.getRate());
                values.put(COLUMN_LAST_UPDATE, DateUtils.getCurrentDate());

                db.insertWithOnConflict(TABLE_RATES, null, values,
                        SQLiteDatabase.CONFLICT_REPLACE);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    @SuppressLint("Range")
    public Collection<? extends ExchangeRate> getTodayRates() {
        List<ExchangeRate> rates = new ArrayList<>();
        String today = DateUtils.getCurrentDate();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RATES,
                new String[]{COLUMN_CURRENCY, COLUMN_RATE},
                COLUMN_LAST_UPDATE + " = ?",
                new String[]{today},
                null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                rates.add(new ExchangeRate(
                        cursor.getString(cursor.getColumnIndex(COLUMN_CURRENCY)),
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_RATE))
                ));
            }
            cursor.close();
        }
        db.close();
        return rates;
    }

    public boolean hasTodayData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String today = DateUtils.getCurrentDate();

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_RATES +
                " WHERE " + COLUMN_LAST_UPDATE + " = ?", new String[]{today});

        boolean hasData = false;
        if (cursor != null) {
            cursor.moveToFirst();
            hasData = cursor.getInt(0) > 0;
            cursor.close();
        }
        db.close();
        return hasData;
    }


}