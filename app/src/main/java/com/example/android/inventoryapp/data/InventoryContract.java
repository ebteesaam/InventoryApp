package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

/**
 * Created by ebtesam on 1/9/2018 AD.
 */

public class InventoryContract {
    private InventoryContract() {
    }



    public static final class Entry implements BaseColumns {
    /** Name of database table  */
    public final static String TABLE_NAME = "inventory";

    public final static String _ID = BaseColumns._ID;

    public final static String COLUMN_INVENTORY_NAME ="name";

    public final static String COLUMN_INVENTORY_PRICE = "price";

    public final static String COLUMN_INVENTORY_QUANTITY = "quantity";
    public final static String COLUMN_INVENTORY_IMAGE = "image";

    public final static String COLUMN_INVENTORY_SUPPLIER_NAME = "supplierName";
    public final static String COLUMN_INVENTORY_SUPPLIER_EMAIL = "supplierEmail";
     public final static String COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER = "supplierPhoneNumber";



}}


