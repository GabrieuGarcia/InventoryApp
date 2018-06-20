package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.BaseColumns._ID;
import static com.example.android.inventoryapp.IncludeEditActivity.getBitmapAsByteArray;
import static com.example.android.inventoryapp.ProductSQLiteContract.ProductEntry.COLUMN_QTD;
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
        ((TextView) findViewById(R.id.email_product)).setText(mProduct.getEmail());
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
        db.update(TABLE_NAME, cv, _ID + "=" + String.valueOf(mProduct.getId()), null);
        mProduct.setQtd(newQtd);
        productQtdTv.setText(String.valueOf(newQtd));
        db.close();
    }

    public void deleteProduct(View view) {
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
    }

    public void detailSalvarProduto(){

        EditText ed = (EditText) findViewById(R.id.edit_product_name);
        String name = ed.getText().toString().trim();
        ed = (EditText) findViewById(R.id.edit_product_value);
        String value = ed.getText().toString().trim();
        ed = (EditText) findViewById(R.id.edit_email_provider);
        String email = ed.getText().toString().trim();
        ImageView imageView = (ImageView) findViewById(R.id.product_image);

        if(name.isEmpty() || value.isEmpty() || value.equals("0") || email.isEmpty() || imageView.getDrawable() == null ) {
            Toast.makeText(ProductDetailActivity.this, "Preencha os campos do Produto!", Toast.LENGTH_LONG).show();
            return;
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(ProductSQLiteContract.ProductEntry.COLUMN_NAME, name);

        values.put(ProductSQLiteContract.ProductEntry.COLUMN_PRICE, Double.parseDouble(value));

        values.put(ProductSQLiteContract.ProductEntry.COLUMN_EMAIL, email);

        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        byte[] data = getBitmapAsByteArray(bitmap);
        values.put(ProductSQLiteContract.ProductEntry.COLUMN_IMAGE, data);

        values.put(ProductSQLiteContract.ProductEntry.COLUMN_QTD, 0);

        db.insert(ProductSQLiteContract.ProductEntry.TABLE_NAME, null, values);

        Toast.makeText(ProductDetailActivity.this, "Produto " + name + " salvo!", Toast.LENGTH_LONG).show();
        finish();
    }
}
