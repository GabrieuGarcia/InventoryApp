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

        EditText ed = (EditText) findViewById(R.id.edit_product_name);
        String name = ed.getText().toString().trim();
        ed = (EditText) findViewById(R.id.edit_product_value);
        String value = ed.getText().toString().trim();
        ed = (EditText) findViewById(R.id.edit_email_provider);
        String email = ed.getText().toString().trim();
        ImageView imageView = (ImageView) findViewById(R.id.product_image);

        if(name.isEmpty() || value.isEmpty() || value.equals("0") || email.isEmpty() || imageView.getDrawable() == null ) {
            Toast.makeText(IncludeEditActivity.this, "Preencha os campos do Produto!", Toast.LENGTH_LONG).show();
            return;
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(ProductEntry.COLUMN_NAME, name);

        values.put(ProductEntry.COLUMN_PRICE, Double.parseDouble(value));

        values.put(ProductEntry.COLUMN_EMAIL, email);

        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        byte[] data = getBitmapAsByteArray(bitmap);
        values.put(ProductEntry.COLUMN_IMAGE, data);

        values.put(ProductEntry.COLUMN_QTD, 0);

        db.insert(ProductEntry.TABLE_NAME, null, values);

        Toast.makeText(IncludeEditActivity.this, "Produto " + name + " salvo!", Toast.LENGTH_LONG).show();
        finish();
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
