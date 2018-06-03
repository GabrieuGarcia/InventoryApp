package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static android.provider.BaseColumns._ID;
import static com.example.android.inventoryapp.ProductSQLiteContract.ProductEntry.COLUMN_NAME_QTD;
import static com.example.android.inventoryapp.ProductSQLiteContract.ProductEntry.TABLE_NAME;

public class ProductDetailActivity extends AppCompatActivity {
    Product mProduct;
    ProductDbHelper mDbHelper;
    TextView productQtdTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_detail);
        mDbHelper = ProductDbHelper.getInstance(ProductDetailActivity.this);
        mProduct = (Product) getIntent().getSerializableExtra("PRODUCT");
        ((TextView) findViewById(R.id.product_name)).setText(mProduct.getName());
        ((TextView) findViewById(R.id.product_price)).setText(String.valueOf(mProduct.getPrice()));
        productQtdTv = (TextView) findViewById(R.id.product_qtd);
        productQtdTv.setText(String.valueOf(mProduct.getQtd()));
    }

    public void incrementOrDecrement(View view) {
        Boolean increment = Boolean.valueOf(view.getTag().toString());
        int newQtd = increment ? mProduct.getQtd() + 1 : mProduct.getQtd() + -1;
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_QTD, newQtd);
        db.update(TABLE_NAME, cv, _ID + "=" + String.valueOf(mProduct.getId()), null);
        mProduct.setQtd(newQtd);
        productQtdTv.setText(String.valueOf(newQtd));
        db.close();
    }

    public void deleteProduct(View view) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL("delete from "+TABLE_NAME+" where " + _ID + "='"+mProduct.getId()+"'");
        db.close();
        finish();
    }
}
