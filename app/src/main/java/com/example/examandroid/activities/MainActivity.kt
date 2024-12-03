package com.example.examandroid.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examandroid.DAO.MovimientosDAO
import com.example.examandroid.adapters.AdapterMain
import com.example.examandroid.data.Movimientos
import com.example.examandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listaMovimientos: MutableList<Movimientos> = mutableListOf()
   private lateinit var adapter: AdapterMain
    private val dao=MovimientosDAO(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Binding para usar los objetos de mi pantalla
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        // Configuración del RecyclerView
      /*  adapter = AdapterMain(listaMovimientos)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }*/

        adapter = AdapterMain(listaMovimientos)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //actualizar_datos_recyclerview()


        //listener del boton añadir
        binding.menuAdd.setOnClickListener(){

            add_movimientos()
        }




    }

    override fun onResume() {
        super.onResume()
    actualizar_datos_recyclerview()


    }
    private fun actualizar_datos_recyclerview() {
        // Actualizar la lista desde el DAO
        listaMovimientos.clear() // Limpia la lista actual
        listaMovimientos.addAll(dao.findAll()) // Supongamos que `getAll` devuelve la lista actualizada

        // Notificar al adaptador del cambio

        adapter.notifyDataSetChanged()

    }

    private fun add_movimientos() {
        val intent: Intent = Intent(this, addActivity::class.java)

        startActivity(intent)
    }
}