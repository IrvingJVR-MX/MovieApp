package com.example.movieapp.ui.peliculaLista.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.PeliculaItemBinding
import com.example.movieapp.repositorio.model.Pelicula
import com.example.movieapp.utils.IListListener
import com.squareup.picasso.Picasso

class AdapterPelicula(
    private val peliculaLista: List<Pelicula>,
    private val listener: IListListener
) :
    RecyclerView.Adapter<AdapterPelicula.ViewHolder>() {

    class ViewHolder(val binding: PeliculaItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun LlenarInfo(pelicula: Pelicula) {
            val url = pelicula.poster
            Picasso.get()
                .load(url)
                .into(binding.ivPelicula)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PeliculaItemBinding.inflate(inflater, null, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = peliculaLista[position]
        holder.LlenarInfo(pelicula)
        holder.binding.ivPelicula.setOnClickListener {
            listener.detallePelicula(pelicula)
        }
    }

    override fun getItemCount() = peliculaLista.size
}