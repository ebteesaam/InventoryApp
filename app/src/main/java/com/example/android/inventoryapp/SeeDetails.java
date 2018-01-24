package com.example.android.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.DbHelper;
import com.example.android.inventoryapp.data.InventoryContract;
import com.example.android.inventoryapp.data.InventoryCursorAdapter;

public class SeeDetails extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int EXISTING_PET_LOADER = 0;
    ViewDetails seeDetails;
    private TextView mNameEditText;
    //    private EditText mEditText;
    private TextView mPriceEditText;
    private TextView mImageEditText, mSupplierName, mSupplierEmail, mSupplierNumber;
    private TextView mQuantityEditText;
    private Uri mCurrentInventoryUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_details);

        Intent intent = getIntent();
        mCurrentInventoryUri = intent.getData();
        if (mCurrentInventoryUri == null) {/////
            setTitle(("view product"));
            invalidateOptionsMenu();
        }
//mEditText=findViewById(R.id.edit_inventory_name);
        mNameEditText = findViewById(R.id.delails_inventory_name);
        mPriceEditText = findViewById(R.id.delails_inventory_price);
        mQuantityEditText = findViewById(R.id.delails_inventory_quantity);
        mImageEditText = findViewById(R.id.delails_inventory_image);
        mSupplierName = findViewById(R.id.delails_inventory_supplier_name);
        mSupplierEmail = findViewById(R.id.delails_inventory_supplier_email);
        mSupplierNumber = findViewById(R.id.delails_inventory_supplier_phone);
        ListView listView = findViewById(R.id.list);
//mNameEditText.setText(mEditText.getText().toString());
        LinearLayout see = findViewById(R.id.see);
        seeDetails = new ViewDetails(this, null);
        // listView.setAdapter(seeDetails);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                InventoryContract.Entry._ID,
                InventoryContract.Entry.COLUMN_INVENTORY_NAME,
                InventoryContract.Entry.COLUMN_INVENTORY_PRICE,
                InventoryContract.Entry.COLUMN_INVENTORY_QUANTITY,
                InventoryContract.Entry.COLUMN_INVENTORY_IMAGE,
                InventoryContract.Entry.COLUMN_INVENTORY_SUPPLIER_NAME,
                InventoryContract.Entry.COLUMN_INVENTORY_SUPPLIER_EMAIL,
                InventoryContract.Entry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER};
        return new CursorLoader(this, InventoryContract.Entry.CONTENT_URI, projection, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {
            // Find the columns of pet attributes that we're interested in
            int nameColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_QUANTITY);
            int imagetColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_IMAGE);
            int sunameColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_SUPPLIER_NAME);
            int subemailColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_SUPPLIER_EMAIL);
            int phoneColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER);

            // Extract out the value from the Cursor for the given column index
            String nameString = cursor.getString(nameColumnIndex);
            double price = cursor.getDouble(priceColumnIndex);
            int quantity = cursor.getInt(quantityColumnIndex);
            String image = cursor.getString(imagetColumnIndex);
            String suppliername = cursor.getString(sunameColumnIndex);
            String supplierEmail = cursor.getString(subemailColumnIndex);
            String supplierphone = cursor.getString(phoneColumnIndex);
            // Update the views on the screen with the values from the database
            mNameEditText.setText(nameString);
            mPriceEditText.setText(String.format("%.2f", price));
            mQuantityEditText.setText(Integer.toString(quantity));
            mImageEditText.setText(image);
            mSupplierName.setText(suppliername);
            mSupplierEmail.setText(supplierEmail);
            mSupplierNumber.setText(supplierphone);
        }
        //seeDetails.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        seeDetails.swapCursor(null);
    }

}
