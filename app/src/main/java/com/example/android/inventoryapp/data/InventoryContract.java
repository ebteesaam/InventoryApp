package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

/**
 * Created by ebtesam on 1/9/2018 AD.
 */

public class InventoryContract {
    private InventoryContract() {
    }



    public static final class Entry implements BaseColumns {
    /** Name of database table for pets */
    public final static String TABLE_NAME = "inventory";

    /**
     * Unique ID number for the pet (only for use in the database table).
     *
     * Type: INTEGER
     */
    public final static String _ID = BaseColumns._ID;

    /**
     * Name of the pet.
     *
     * Type: TEXT
     */
    public final static String COLUMN_INVENTORY_NAME ="name";

    /**
     *
     *
     * Type: TEXT
     */
    public final static String COLUMN_INVENTORY_PRICE = "price";


    /**
     *
     *
     * Type: INTEGER
     */
    public final static String COLUMN_INVENTORY_QUANTITY = "quantity";
    public final static String COLUMN_INVENTORY_IMAGE = "image";

    public final static String COLUMN_INVENTORY_SUPPLIER_NAME = "supplierName";
    public final static String COLUMN_INVENTORY_SUPPLIER_EMAIL = "supplierEmail";
     public final static String COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER = "supplierPhoneNumber";



}}


