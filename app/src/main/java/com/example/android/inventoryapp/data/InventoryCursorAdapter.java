package com.example.android.inventoryapp.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.CatalogActivity;
import com.example.android.inventoryapp.EditorActivity;
import com.example.android.inventoryapp.R;


/**
 * Created by ebtesam on 1/11/2018 AD.
 */

public class InventoryCursorAdapter extends CursorAdapter {
    EditorActivity editorActivity;
    private double price;

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
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView nameTextView = view.findViewById(R.id.name);
        TextView priceTextView = view.findViewById(R.id.price);
        TextView summaryTextView = view.findViewById(R.id.summary);
        final Button saleB = view.findViewById(R.id.sale);
        saleB.setFocusable(false);
        saleB.setFocusableInTouchMode(false);

        // Find the columns of pet attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.Entry.COLUMN_INVENTORY_PRICE);
        editorActivity = new EditorActivity();
        // Read the pet attributes from the Cursor for the current pet
        String Name = cursor.getString(nameColumnIndex);
        final int quantity = cursor.getInt(quantityColumnIndex);
        price = cursor.getDouble(priceColumnIndex);
        if (TextUtils.isEmpty(Name)) {
            Name = context.getString(R.string.unknown_name);
        }

        saleB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentResolver resolver = context.getContentResolver();


                ContentValues values = new ContentValues();


                if (quantity > 0) {

                    // Create a new uri for this product ( ListItem)
                    Uri CurrentUri = ContentUris.withAppendedId(InventoryContract.Entry.CONTENT_URI, CatalogActivity.lo);

                    // Present a new variable to send the reduced quantity to database
                    int currentAvailableQuantity = quantity;
                    currentAvailableQuantity -= 1;


                    values.put(InventoryContract.Entry.COLUMN_INVENTORY_QUANTITY, currentAvailableQuantity);

                    resolver.update(CurrentUri, values, null, null);

                    context.getContentResolver().notifyChange(CurrentUri, null);
                } else {

                    Toast.makeText(v.getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Update the TextViews with the attributes for the current pet
        nameTextView.setText(Name);
        summaryTextView.setText(Integer.toString(quantity));
        priceTextView.setText("$" + String.format("%.2f", price));

    }


}