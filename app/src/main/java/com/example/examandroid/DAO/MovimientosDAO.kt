package com.example.examandroid.DAO

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.examandroid.data.Movimientos
import com.example.examandroid.utils.DataBaseManager

class MovimientosDAO(val context: Context) {
    private lateinit var db: SQLiteDatabase
    var list: MutableList<Movimientos> = mutableListOf()


    private fun open() {
        db = DataBaseManager(context).writableDatabase
    }

    private fun close() {
        db.close()
    }

    fun insert(movimientos: Movimientos) : Long {

        var id = -1L//inicializo así porque es un Long y el valor si falla el insert será de -1
        open()

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(Movimientos.COLUMN_CANTIDAD, movimientos.cantidad)
            put(Movimientos.COLUMN_FECHA, movimientos.fecha)


        }

        try {
            // Insert the new row, returning the primary key value of the new row
            id = db.insert(Movimientos.TABLE_NAME, null, values)

        } catch (e: Exception) {
            Log.e("DB", e.stackTraceToString())
        } finally {
            close()
        }

        return id
    }

    fun findAll(): MutableList<Movimientos> {

        open()

        val projection = arrayOf(Movimientos.COLUMN_ID, Movimientos.COLUMN_CANTIDAD, Movimientos.COLUMN_FECHA)

        try {
            val cursor = db.query(
                Movimientos.TABLE_NAME,                    // The table to query
                projection,                         // The array of columns to return (pass null to get all)
                null,                       // The columns for the WHERE clause
                null,                   // The values for the WHERE clause
                null,                       // don't group the rows
                null,                         // don't filter by row groups
                null                         // The sort order
            )

            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(Movimientos.COLUMN_ID))
                val cantidad = cursor.getInt(cursor.getColumnIndexOrThrow(Movimientos.COLUMN_CANTIDAD))
                val fecha = cursor.getString(cursor.getColumnIndexOrThrow(Movimientos.COLUMN_FECHA))

                val movimientos = Movimientos(id, cantidad , fecha )

                list.add(movimientos)
            }
        } catch (e: Exception) {
            Log.e("DB", e.stackTraceToString())
        } finally {
            close()
        }
        return list
    }

    }


