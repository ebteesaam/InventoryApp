package com.example.android.inventoryapp.data;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.inventoryapp.R;

/**
 * Created by ebtesam on 1/11/2018 AD.
 */

public class InventoryCursorAdapter extends CursorAdapter {

    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        TextView summaryTextView = (TextView) view.findViewById(R.id.summary);

        if(cursor.moveToFirst()) {
        // Find the columns of pet attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_PRICE);


        // Read the pet attributes from the Cursor for the current pet
        String Name = cursor.getString(nameColumnIndex);
       // int quantity = cursor.getInt(quantityColumnIndex);
       // double price = cursor.getDouble(priceColumnIndex);

        // Update the TextViews with the attributes for the current pet
        nameTextView.setText(Name);
      //  summaryTextView.setText(Integer.toString(quantity));
       // priceTextView.setText("$" + String.format("%.2f", price));
    }}

}