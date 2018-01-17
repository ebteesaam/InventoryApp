package com.example.android.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ebtesam on 1/9/2018 AD.
 */

public class DbHelper extends SQLiteOpenHelper {
    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "product.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a String that contains the SQL statement to create the  table
        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " + InventoryContract.Entry.TABLE_NAME + " ("
                + InventoryContract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryContract.Entry.COLUMN_INVENTORY_NAME + " TEXT NOT NULL, "
                + InventoryContract.Entry.COLUMN_INVENTORY_PRICE + " INTEGER NOT NULL,"
                + InventoryContract.Entry.COLUMN_INVENTORY_QUANTITY + " INTEGER NOT NULL DEFAULT 1,"
                + InventoryContract.Entry.COLUMN_INVENTORY_IMAGE + " INTEGER,"
                + InventoryContract.Entry.COLUMN_INVENTORY_SUPPLIER_NAME + " TEXT NOT NULL,"
                + InventoryContract.Entry.COLUMN_INVENTORY_SUPPLIER_EMAIL + " TEXT NOT NULL,"
                + InventoryContract.Entry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER + " INTEGER );";

        // Execute the SQL statement
        sqLiteDatabase.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

