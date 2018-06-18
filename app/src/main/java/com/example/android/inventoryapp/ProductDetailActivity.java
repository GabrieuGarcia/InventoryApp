package com.example.android.inventoryapp;

import android.R;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static android.provider.BaseColumns._ID;
import static com.example.android.inventoryapp.ProductSQLiteContract.ProductEntry.*;

<<<<<<<HEAD
        =======
        >>>>>>>a2a2ee9702608df541b1fbb8ed82f1b0accbf599
        <<<<<<<HEAD
        =======
        >>>>>>>a2a2ee9702608df541b1fbb8ed82f1b0accbf599

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
<<<<<<< HEAD
        ((TextView) findViewById(R.id.email_product)).setText(mProduct.getEmail());
=======
>>>>>>> a2a2ee9702608df541b1fbb8ed82f1b0accbf599
        productQtdTv = (TextView) findViewById(R.id.product_qtd);
        productQtdTv.setText(String.valueOf(mProduct.getQtd()));
    }

    public void incrementOrDecrement(View view) {
        Boolean increment = Boolean.valueOf(view.getTag().toString());
        int newQtd = increment ? mProduct.getQtd() + 1 : mProduct.getQtd() + -1;

        if (newQtd < 0) {
            newQtd = 0;
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_QTD, newQtd);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_QTD, newQtd);
        db.update(TABLE_NAME, cv, _ID + "=" + String.valueOf(mProduct.getId()), null);
        mProduct.setQtd(newQtd);
        productQtdTv.setText(String.valueOf(newQtd));
        db.close();
    }

    public void deleteProduct(View view) {
<<<<<<< HEAD

        new AlertDialog.Builder(this)
                .setMessage("Tem certeza que deseja deletar este Produto?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        SQLiteDatabase db = mDbHelper.getWritableDatabase();
                        db.execSQL("delete from " + TABLE_NAME + " where " + _ID + "='" + mProduct.getId() + "'");
                        db.close();
                        finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void buyProduct(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, mProduct.getEmail());
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

        startActivity(Intent.createChooser(intent, "Send Email"));
=======
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL("delete from "+TABLE_NAME+" where " + _ID + "='"+mProduct.getId()+"'");
        db.close();
        finish();
>>>>>>> a2a2ee9702608df541b1fbb8ed82f1b0accbf599
    }
}
