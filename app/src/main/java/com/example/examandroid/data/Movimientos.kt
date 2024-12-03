package com.example.examandroid.data

data class Movimientos(val id: Int, val cantidad: Int, var fecha: String){


    // Constructor secundario sin el 'id', útil cuando el 'id' es autoincremental
    constructor(cantidad: Int, fecha: String) : this(0, cantidad, fecha) {
        // El 'id' podría ser 0 por defecto, luego se asignará automáticamente al insertar en la base de datos.
    }
    companion object {
        const val TABLE_NAME = "movimientos"
        const val COLUMN_ID = "id"
        const val COLUMN_CANTIDAD = "cantidad"
        const val COLUMN_FECHA = "fecha"

    }

}
