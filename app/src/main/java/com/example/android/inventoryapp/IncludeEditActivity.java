package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.inventoryapp.ProductSQLiteContract.ProductEntry;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class IncludeEditActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMG = 1;
    ProductDbHelper mDbHelper;

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include_edit);
        mDbHelper = ProductDbHelper.getInstance(IncludeEditActivity.this);
    }

    public void salvarProduto(View view) {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        EditText editText = (EditText) findViewById(R.id.edit_product_name);
<<<<<<< HEAD
        String name = editText.getText().toString().trim();
        values.put(ProductEntry.COLUMN_NAME, name);

        editText = (EditText) findViewById(R.id.edit_product_value);
        double price = Double.parseDouble(editText.getText().toString());
        values.put(ProductEntry.COLUMN_PRICE, price);

        editText = (EditText) findViewById(R.id.edit_email_provider);
        String email = editText.getText().toString().trim();
        values.put(ProductEntry.COLUMN_EMAIL, email);
=======
        String name = editText.getText().toString();
        values.put(ProductEntry.COLUMN_NAME_NAME, name);

        editText = (EditText) findViewById(R.id.edit_product_value);
        double price = Double.parseDouble(editText.getText().toString());
        values.put(ProductEntry.COLUMN_NAME_PRICE, price);
>>>>>>> a2a2ee9702608df541b1fbb8ed82f1b0accbf599

        ImageView imageView = (ImageView) findViewById(R.id.product_image);
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        byte[] data = getBitmapAsByteArray(bitmap);
<<<<<<< HEAD
        values.put(ProductEntry.COLUMN_IMAGE, data);

        values.put(ProductEntry.COLUMN_QTD, 0);

        if (!name.isEmpty() && price > 0D && data.length > 0) {

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(ProductEntry.TABLE_NAME, null, values);

            Toast.makeText(IncludeEditActivity.this, "Produto " + name + " salvo!", Toast.LENGTH_LONG).show();

            Intent myIntent = new Intent(IncludeEditActivity.this, ProductsActivity.class);
            startActivity(myIntent);

        } else {
            Toast.makeText(IncludeEditActivity.this, "Preencha os campos do Produto!", Toast.LENGTH_LONG).show();
        }
=======
        values.put(ProductEntry.COLUMN_NAME_IMAGE, data);

        values.put(ProductEntry.COLUMN_NAME_QTD, 0);


// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ProductEntry.TABLE_NAME, null, values);
        Toast.makeText(IncludeEditActivity.this, "Produto salvo!" + newRowId, Toast.LENGTH_LONG).show();
>>>>>>> a2a2ee9702608df541b1fbb8ed82f1b0accbf599
    }

    public void getImageFromGallery(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ImageView imageView = (ImageView) findViewById(R.id.product_image);
                imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(IncludeEditActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(IncludeEditActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}
