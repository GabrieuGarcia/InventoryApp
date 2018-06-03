package com.example.android.inventoryapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductsLoader extends AsyncTaskLoader<List<Product>> {
    Context mContext;

    public ProductsLoader(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Product> loadInBackground() {
        List<Product> productsList = new ArrayList<>();
        ProductDbHelper dbHelper = ProductDbHelper.getInstance(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + ProductSQLiteContract.ProductEntry.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndex(ProductSQLiteContract.ProductEntry._ID)));
                product.setName(cursor.getString(cursor.getColumnIndex(ProductSQLiteContract.ProductEntry.COLUMN_NAME_NAME)));
                product.setPrice(cursor.getDouble(cursor.getColumnIndex(ProductSQLiteContract.ProductEntry.COLUMN_NAME_PRICE)));
                product.setImage(cursor.getBlob(cursor.getColumnIndex(ProductSQLiteContract.ProductEntry.COLUMN_NAME_IMAGE)));
                product.setQtd(cursor.getInt(cursor.getColumnIndex(ProductSQLiteContract.ProductEntry.COLUMN_NAME_QTD)));
                productsList.add(product);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return productsList;
    }
}
