package com.example.android.inventoryapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Product>> {
    private static final int PRODUCTS_LOADER_ID = 1;
    private ProductsAdapter mAdapter;
    private ListView productsListView;
    private ArrayList<Product> productsArrayList = new ArrayList<>();
    private RelativeLayout mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        mEmptyStateTextView = (RelativeLayout) findViewById(R.id.empty_view);
        productsListView = (ListView) findViewById(R.id.list);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(PRODUCTS_LOADER_ID,null,this);

        mAdapter = new ProductsAdapter(this, productsArrayList);

        productsListView.setEmptyView(mEmptyStateTextView);
        productsListView.setAdapter(mAdapter);
    }

    @Override
    public Loader<List<Product>> onCreateLoader(int id, Bundle args) {
        return new ProductsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Product>> loader, List<Product> data) {
        ProductsAdapter adapterForSearchResults = new ProductsAdapter(this, data);
        productsListView.setAdapter(adapterForSearchResults);
        productsArrayList = (ArrayList<Product>) data;
        if (data.isEmpty()) {
            productsListView.setEmptyView(mEmptyStateTextView);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Product>> loader) {
        mAdapter.clear();
    }

    public void addProduct(View view) {
        Intent myIntent = new Intent(ProductsActivity.this, IncludeEditActivity.class);
        startActivity(myIntent);
    }
}
