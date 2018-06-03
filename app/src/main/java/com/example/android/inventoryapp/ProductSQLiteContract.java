package com.example.android.inventoryapp;

import android.provider.BaseColumns;

/**
 * Created by lucas on 29/05/18.
 */

public final class ProductSQLiteContract {
    private ProductSQLiteContract() {
    }

    public static class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_QTD = "qtd";
    }


}
