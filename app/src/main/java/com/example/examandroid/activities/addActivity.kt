package com.example.examandroid.activities

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examandroid.DAO.MovimientosDAO
import com.example.examandroid.R
import com.example.examandroid.data.Movimientos
import com.example.examandroid.databinding.ActivityAddBinding
import java.util.Calendar

class addActivity : AppCompatActivity() {

   lateinit var binding: ActivityAddBinding
    val dao= MovimientosDAO(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgCalendar.setOnClickListener(){
            mostrar_cuadro_dialogo_fecha()
        }
        binding.btGuardar.setOnClickListener(){
            guardar_en_ddbb()
            finish()

        }

    }

    private fun guardar_en_ddbb() {

        var cantidad = binding.editCantidad.text.toString()

        var fecha= binding.txtDate.text

        var movGrabar = Movimientos(cantidad.toInt(),fecha.toString())

        dao.insert(movGrabar)







    }


    private fun mostrar_cuadro_dialogo_fecha() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this@addActivity,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val formattedDate = "$selectedYear-${selectedMonth + 1}-${selectedDayOfMonth}"

                binding.txtDate.text=formattedDate

            },
            year,
            month,
            day
        )

        val minDateCalendar = Calendar.getInstance()
        minDateCalendar.set(1995, Calendar.JUNE, 17)
        datePickerDialog.datePicker.minDate = minDateCalendar.timeInMillis

        datePickerDialog.show()

    }
}