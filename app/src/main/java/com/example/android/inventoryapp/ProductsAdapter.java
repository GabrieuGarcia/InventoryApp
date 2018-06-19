package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.example.android.inventoryapp.ProductSQLiteContract.ProductEntry.COLUMN_QTD;
import static com.example.android.inventoryapp.ProductSQLiteContract.ProductEntry.TABLE_NAME;

/**
 * This class adapts the layout of the app.
 *
 * Created by Gabriel on 12/03/2018.
 */
public class ProductsAdapter extends ArrayAdapter<Product> {
    ProductDbHelper mDbHelper;

    public ProductsAdapter(Context context, List<Product> bookList) {
        super(context, 0, bookList);
        mDbHelper = ProductDbHelper.getInstance(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Product currentProduct = getItem(position);

        TextView titleView = (TextView) listItemView.findViewById(R.id.id_product_name);
        titleView.setText(currentProduct != null ? currentProduct.getName() : null);

        TextView sectionView = (TextView) listItemView.findViewById(R.id.id_value);
        sectionView.setText(currentProduct.getPrice().toString());

        final TextView qtdView = (TextView) listItemView.findViewById(R.id.id_quantity);
        qtdView.setText("quant: " + String.valueOf(currentProduct.getQtd()));

        Button btnSell = (Button) listItemView.findViewById(R.id.btn_sell);
        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentProduct.getQtd() == 0) {
                    return;
                }
                int newQtd = currentProduct.getQtd() - 1;
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_QTD, newQtd);
                db.update(TABLE_NAME, cv, _ID + "=" + String.valueOf(currentProduct.getId()), null);
                currentProduct.setQtd(newQtd);
                qtdView.setText("quant: " + String.valueOf(currentProduct.getQtd()));
                db.close();
            }
        });

        LinearLayout productContainer = (LinearLayout) listItemView.findViewById(R.id.product_container);
        productContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                intent.putExtra("PRODUCT", currentProduct);
                getContext().startActivity(intent);
            }
        });
        return listItemView;
    }
}