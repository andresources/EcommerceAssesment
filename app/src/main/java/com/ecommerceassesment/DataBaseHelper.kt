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
                         ${DataBaseConstants.PRODUCT_DESCRIPTION} TEXT
                )
        """.trimMargin()

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(product_title: String): Long {

        val values = ContentValues().apply {
            put(DataBaseConstants.PRODUCT_NAME, product_title)
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

                datalist.add(ProductDBPojo(id, product_title))
            }

        }
        return datalist
    }


}
