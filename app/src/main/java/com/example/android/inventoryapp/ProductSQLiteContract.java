package com.example.android.inventoryapp;

<<<<<<< HEAD
=======
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
>>>>>>> a2a2ee9702608df541b1fbb8ed82f1b0accbf599
import android.provider.BaseColumns;

/**
 * Created by lucas on 29/05/18.
 */

public final class ProductSQLiteContract {
    private ProductSQLiteContract() {
    }

    public static class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
<<<<<<< HEAD
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_QTD = "qtd";
=======
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_QTD = "qtd";
>>>>>>> a2a2ee9702608df541b1fbb8ed82f1b0accbf599
    }


}
