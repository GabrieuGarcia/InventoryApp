package com.example.android.inventoryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by lucas on 29/05/18.
 */

public final class ProductSQLiteContract {
    private ProductSQLiteContract() {
    }

    public static class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_QTD = "qtd";
    }


}
