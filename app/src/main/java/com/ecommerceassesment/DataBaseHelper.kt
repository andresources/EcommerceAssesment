package com.ecommerceassesment

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper (context: Context) : SQLiteOpenHelper(
    context,
    DataBaseConstants.DATABASE_NAME,
    null,
    DataBaseConstants.DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
                  CREATE TABLE ${DataBaseConstants.TABLE_NAME} (
                         ${DataBaseConstants.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                         ${DataBaseConstants.PRODUCT_NAME} TEXT,
                         ${DataBaseConstants.PRODUCT_DESCRIPTION} TEXT,
                         ${DataBaseConstants.PRODUCT_PRICE} INTEGER,
                         ${DataBaseConstants.PRODUCT_COUNT} INTEGER
                )
        """.trimMargin()

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(product_title: String,product_description: String, pcount:Int,price: Int ): Long {

        val values = ContentValues().apply {
            put(DataBaseConstants.PRODUCT_NAME, product_title)
            put(DataBaseConstants.PRODUCT_DESCRIPTION, product_description)
            put(DataBaseConstants.PRODUCT_COUNT, pcount)
            put(DataBaseConstants.PRODUCT_PRICE, price)
        }

        return writableDatabase.insert(DataBaseConstants.TABLE_NAME, null, values)
    }

    fun readData(): List<ProductDBPojo> {
        val datalist = mutableListOf<ProductDBPojo>()

        val curser: Cursor = readableDatabase.query(
            DataBaseConstants.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null,
        )

        with(curser) {
            while (moveToNext()) {
                var id = getLong(getColumnIndexOrThrow(DataBaseConstants.COLUMN_ID))
                val product_title = getString(getColumnIndexOrThrow(DataBaseConstants.PRODUCT_NAME))
                val product_description = getString(getColumnIndexOrThrow(DataBaseConstants.PRODUCT_DESCRIPTION))
                val product_count = getInt(getColumnIndexOrThrow(DataBaseConstants.PRODUCT_COUNT))
                val product_price = getInt(getColumnIndexOrThrow(DataBaseConstants.PRODUCT_PRICE))
                datalist.add(ProductDBPojo(id, product_title,product_description,product_count,product_price))
            }

        }
        return datalist
    }
    fun delete(pname: String) : Int{
        val selection = "${DataBaseConstants.PRODUCT_NAME} = ?"
        val selectionArgs = arrayOf(pname)
        return writableDatabase.delete(DataBaseConstants.TABLE_NAME,selection,selectionArgs)
    }
    fun update(pname: String,pcount : Int) : Int{
        val values = ContentValues().apply {
            put(DataBaseConstants.PRODUCT_COUNT, pcount)
        }
        val selection = "${DataBaseConstants.PRODUCT_NAME} = ?"
        val selectionArgs = arrayOf(pname)
        return writableDatabase.update(DataBaseConstants.TABLE_NAME,values, selection, selectionArgs)
    }
}
