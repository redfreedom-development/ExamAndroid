package com.example.examandroid.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examandroid.data.Movimientos

class DataBaseManager(context: Context) : SQLiteOpenHelper( context, DATABASE_NAME, null, DATABASE_VERSION
) {
    companion object {
        const val DATABASE_NAME = "exam.db"
        const val DATABASE_VERSION = 1



        private const val CREATE_TABLE = """
            CREATE TABLE ${Movimientos.TABLE_NAME} (
                ${Movimientos.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${Movimientos.COLUMN_CANTIDAD} INTEGER,
                ${Movimientos.COLUMN_FECHA} TEXT 
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${Movimientos.TABLE_NAME}")
        onCreate(db)
    }




}