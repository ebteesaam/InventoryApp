package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.inventoryapp.data.DbHelper;
import com.example.android.inventoryapp.data.InventoryContract;
import com.example.android.inventoryapp.data.InventoryContract.Entry;

public class CatalogActivity extends AppCompatActivity {
    private DbHelper mDbHelper;
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper=new DbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
    }
    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] project={
                InventoryContract.Entry._ID,
                Entry.COLUMN_INVENTORY_NAME,
                Entry.COLUMN_INVENTORY_PRICE,
                Entry.COLUMN_INVENTORY_QUANTITY,
                Entry.COLUMN_INVENTORY_IMAGE,
                Entry.COLUMN_INVENTORY_SUPPLIER_NAME,
                Entry.COLUMN_INVENTORY_SUPPLIER_EMAIL,
                Entry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER
        };
        Cursor cursor=db.query(Entry.TABLE_NAME,project,null,null,null,null,null);

        // pets table in the database).
        TextView displayView = findViewById(R.id.text_view_pet);
        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the

            displayView.setText("product: " + cursor.getCount());
            displayView.append(Entry._ID+"  "+
                    Entry.COLUMN_INVENTORY_NAME+"  "+
                    Entry.COLUMN_INVENTORY_PRICE+"  "+
                    Entry.COLUMN_INVENTORY_QUANTITY+"  "+
                    Entry.COLUMN_INVENTORY_IMAGE+"  "+
            Entry.COLUMN_INVENTORY_SUPPLIER_NAME+"  "+
            Entry.COLUMN_INVENTORY_SUPPLIER_EMAIL+"  "+
            Entry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER);

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(Entry._ID);
            int nameColumnIndex = cursor.getColumnIndex(Entry.COLUMN_INVENTORY_NAME);
            int priceColumnIndex = cursor.getColumnIndex(Entry.COLUMN_INVENTORY_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(Entry.COLUMN_INVENTORY_QUANTITY);
            int imageColumnIndex = cursor.getColumnIndex(Entry.COLUMN_INVENTORY_IMAGE);
            int suppliernameColumnIndex = cursor.getColumnIndex(Entry.COLUMN_INVENTORY_SUPPLIER_NAME);
            int supplierEmailColumnIndex = cursor.getColumnIndex(Entry.COLUMN_INVENTORY_SUPPLIER_EMAIL);
            int supplierphoneColumnIndex = cursor.getColumnIndex(Entry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER);


            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentprice = cursor.getString(priceColumnIndex);
                int currentquantity = cursor.getInt(quantityColumnIndex);
                int currentimage = cursor.getInt(imageColumnIndex);
                String currentSupplierN = cursor.getString(suppliernameColumnIndex);
                String currentSupplierE = cursor.getString(supplierEmailColumnIndex);
                long currentSupplierph = cursor.getLong(supplierphoneColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentprice + " - " +
                        currentquantity + " - " +
                        currentimage+ " - " +
                        currentSupplierN + " - " +
                        currentSupplierE + " - " +
                        currentSupplierph));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
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
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(Entry.COLUMN_INVENTORY_NAME, "CD");
        values.put(Entry.COLUMN_INVENTORY_PRICE, 10);
        values.put(Entry.COLUMN_INVENTORY_QUANTITY, 2);
        values.put(Entry.COLUMN_INVENTORY_IMAGE, 0);
        values.put(Entry.COLUMN_INVENTORY_SUPPLIER_NAME, "Jack");
        values.put(Entry.COLUMN_INVENTORY_SUPPLIER_EMAIL, "1dfdf@gg");
        values.put(Entry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER, 333);

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(Entry.TABLE_NAME, null, values);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();

                // Do nothing for now
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

