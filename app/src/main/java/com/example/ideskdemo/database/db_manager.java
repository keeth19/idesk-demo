package com.example.ideskdemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Objects;

class db_manager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "waitersSummaryDB.db";
    private static final int    DATABASE_VERSION = 1;

    db_manager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(db_tables.CREATE_TABLE_DISTRICT);
            db.execSQL(db_tables.CREATE_TABLE_CATEGORY);
            db.execSQL(db_tables.CREATE_TABLE_SUB_CATEGORY);
            db.execSQL(db_tables.CREATE_TABLE_USER);
            db.execSQL(db_tables.CREATE_TABLE_ADVERTISEMENT);
            db.execSQL(db_tables.CREATE_TABLE_RATING);
            db.execSQL(db_tables.CREATE_TABLE_FAVOURITE);

        } catch (Exception e) {
            Log.e(db_manager.class.getSimpleName(), Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
