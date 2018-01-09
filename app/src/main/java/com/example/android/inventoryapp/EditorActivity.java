package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.inventoryapp.data.DbHelper;
import com.example.android.inventoryapp.data.InventoryContract.Entry;

public class EditorActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mPriceEditText;
    private EditText mQuantityEditText, mImageEditText, mSupplierName, mSupplierEmail, mSupplierNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_inventory_name);
        mPriceEditText = (EditText) findViewById(R.id.edit_inventory_price);
        mQuantityEditText = (EditText) findViewById(R.id.edit_inventory_quantity);
        mImageEditText = (EditText) findViewById(R.id.edit_inventory_image);
        mSupplierName = (EditText) findViewById(R.id.edit_inventory_supplier_name);
        mSupplierEmail = (EditText) findViewById(R.id.edit_inventory_supplier_email);
        mSupplierNumber = (EditText) findViewById(R.id.edit_inventory_supplier_phone);

    }

    private void insertPet() {
        String nameString = mNameEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();
        int price = Integer.parseInt(priceString);
        String quantityString = mQuantityEditText.getText().toString().trim();
        int quantity = Integer.parseInt(quantityString);
        String image = mImageEditText.getText().toString().trim();
        String suppliername = mSupplierName.getText().toString().trim();
        String supplierEmail = mSupplierEmail.getText().toString().trim();
        String supplierphone = mSupplierNumber.getText().toString().trim();

        // Create database helper
        DbHelper mDbHelper = new DbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(Entry.COLUMN_INVENTORY_NAME, nameString);
        values.put(Entry.COLUMN_INVENTORY_PRICE, price);
        values.put(Entry.COLUMN_INVENTORY_QUANTITY, quantity);
        values.put(Entry.COLUMN_INVENTORY_IMAGE, image);
        values.put(Entry.COLUMN_INVENTORY_SUPPLIER_NAME, suppliername);
        values.put(Entry.COLUMN_INVENTORY_SUPPLIER_EMAIL, supplierEmail);
        values.put(Entry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER, supplierphone);

        // Insert a new row in the database, returning the ID of that new row.
        long newRowId = db.insert(Entry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Do nothing for now
                insertPet();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}