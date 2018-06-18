package com.example.android.inventoryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static ProductDbHelper mDbHelper;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Product.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProductSQLiteContract.ProductEntry.TABLE_NAME + " (" +
                    ProductSQLiteContract.ProductEntry._ID + " INTEGER PRIMARY KEY," +
<<<<<<< HEAD
                    ProductSQLiteContract.ProductEntry.COLUMN_NAME + " TEXT," +
                    ProductSQLiteContract.ProductEntry.COLUMN_PRICE + " DOUBLE," +
                    ProductSQLiteContract.ProductEntry.COLUMN_IMAGE + " BLOB," +
                    ProductSQLiteContract.ProductEntry.COLUMN_EMAIL + " TEXT," +
                    ProductSQLiteContract.ProductEntry.COLUMN_QTD + " INTEGER)";

=======
                    ProductSQLiteContract.ProductEntry.COLUMN_NAME_NAME + " TEXT," +
                    ProductSQLiteContract.ProductEntry.COLUMN_NAME_PRICE + " DOUBLE," +
                    ProductSQLiteContract.ProductEntry.COLUMN_NAME_QTD + " INTEGER," +
                    ProductSQLiteContract.ProductEntry.COLUMN_NAME_IMAGE + " BLOB)";
>>>>>>> a2a2ee9702608df541b1fbb8ed82f1b0accbf599
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProductSQLiteContract.ProductEntry.TABLE_NAME;

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static ProductDbHelper getInstance(Context context) {
<<<<<<< HEAD
        if (mDbHelper == null) {
=======
        if(mDbHelper == null) {
>>>>>>> a2a2ee9702608df541b1fbb8ed82f1b0accbf599
            mDbHelper = new ProductDbHelper(context);
        }

        return mDbHelper;
    }
}