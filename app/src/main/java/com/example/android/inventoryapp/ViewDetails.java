package com.example.android.inventoryapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.inventoryapp.data.InventoryContract;

/**
 * Created by ebtesam on 1/21/2018 AD.
 */

public class ViewDetails extends CursorAdapter {

    public ViewDetails(Context context, Cursor c) {

        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(R.layout.activity_see_details, viewGroup, false);

    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView mNameEditText = view.findViewById(R.id.delails_inventory_name);
        TextView mPriceEditText = view.findViewById(R.id.delails_inventory_price);
        TextView mQuantityEditText = view.findViewById(R.id.delails_inventory_quantity);
        TextView mImageEditText = view.findViewById(R.id.delails_inventory_image);
        TextView mSupplierName = view.findViewById(R.id.delails_inventory_supplier_name);
        TextView mSupplierEmail = view.findViewById(R.id.delails_inventory_supplier_email);
        TextView mSupplierNumber = view.findViewById(R.id.delails_inventory_supplier_phone);


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

}

