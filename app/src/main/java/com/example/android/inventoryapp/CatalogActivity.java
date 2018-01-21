package com.example.android.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.DbHelper;
import com.example.android.inventoryapp.data.InventoryContract;
import com.example.android.inventoryapp.data.InventoryContract.Entry;
import com.example.android.inventoryapp.data.InventoryCursorAdapter;

public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final int INVENTORY_LOADER=0;
    InventoryCursorAdapter inventoryCursorAdapter;
    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        // Find the ListView which will be populated with the pet data
        ListView listView = (ListView) findViewById(R.id.list);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);
        inventoryCursorAdapter=new InventoryCursorAdapter(this,null);
        listView.setAdapter(inventoryCursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                Uri uri= ContentUris.withAppendedId(InventoryContract.Entry.CONTENT_URI,l);

                intent.setData(uri);
                startActivity(intent);

            }});
        ImageButton call = findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = Entry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER.trim().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
        getLoaderManager().initLoader(INVENTORY_LOADER,null,this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.cd ..
        getMenuInflater().inflate(R.menu.catalog_menu, menu);
        return true;
    }

    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertPet() {

        // Gets the database in write mode
        // Create a ContentValues object where column names are the keys,
        //  attributes are the values.
        ContentValues values = new ContentValues();
        values.put(Entry.COLUMN_INVENTORY_NAME, "CD");
        values.put(Entry.COLUMN_INVENTORY_PRICE, 10);
        values.put(Entry.COLUMN_INVENTORY_QUANTITY, 2);
        values.put(Entry.COLUMN_INVENTORY_IMAGE, 0);
        values.put(Entry.COLUMN_INVENTORY_SUPPLIER_NAME, "Jack");
        values.put(Entry.COLUMN_INVENTORY_SUPPLIER_EMAIL, "1dfdf@gg");
        values.put(Entry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER, 333);
        Uri newUri = getContentResolver().insert(Entry.CONTENT_URI, values);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteAllPets();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                InventoryContract.Entry._ID,
                Entry.COLUMN_INVENTORY_NAME,
                Entry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER,
                Entry.COLUMN_INVENTORY_QUANTITY,
                Entry.COLUMN_INVENTORY_PRICE};
        return new CursorLoader(this,InventoryContract.Entry.CONTENT_URI,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        TextView nameTextView = findViewById(R.id.name);
        TextView priceTextView = findViewById(R.id.price);
        TextView summaryTextView = findViewById(R.id.summary);

        //inventoryCursorAdapter.swapCursor(cursor);
//        if(cursor.moveToFirst()) {
//            int nameColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_NAME);
//            int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_QUANTITY);
//            int priceColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_PRICE);


        // Extract out the value from the Cursor for the given column index

        //          final String Name = cursor.getString(nameColumnIndex);
//            final int quantity = cursor.getInt(quantityColumnIndex);
//            double price = cursor.getDouble(priceColumnIndex);

        //           nameTextView.setText(Name);
//            summaryTextView.setText(Integer.toString(quantity));
//            priceTextView.setText("$" + String.format("%.2f", price));


        inventoryCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        inventoryCursorAdapter.swapCursor(null);

    }

    /**
     * Helper method to delete all pets in the database.
     */
    private void deleteAllPets() {
        int rowsDeleted = getContentResolver().delete(Entry.CONTENT_URI, null, null);
        Log.v("CatalogActivity", rowsDeleted + " rows deleted from products database");
    }
}

