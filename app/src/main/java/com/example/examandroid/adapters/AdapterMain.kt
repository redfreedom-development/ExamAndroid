package com.example.examandroid.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examandroid.data.Movimientos
import com.example.examandroid.databinding.ItemMovimientoBinding

class AdapterMain(
    private val movimientos: MutableList<Movimientos>
) : RecyclerView.Adapter<AdapterMain.MovimientosViewHolder>() {

    // ViewHolder utilizando View Binding
    class MovimientosViewHolder(private val binding: ItemMovimientoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movimientos: Movimientos) {
            binding.idMovimiento.text = "ID: ${movimientos.id}"
            binding.cantidadMovimiento.text = "CANTIDAD: ${movimientos.cantidad}"
            binding.fechaMovimiento.text = "FECHA: ${movimientos.fecha}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovimientosViewHolder {
        val binding = ItemMovimientoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovimientosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovimientosViewHolder, position: Int) {
        holder.bind(movimientos[position])
    }

    override fun getItemCount(): Int = movimientos.size
}