package com.example.movieapp.ui.peliculaLista.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.PadreItemBinding
import com.example.movieapp.repositorio.model.CollectionPelicula
import com.example.movieapp.utils.IListListener

class AdapterPrincipal(
    private val collectionPelicula: List<CollectionPelicula>,
    private val listener: IListListener
) :
    RecyclerView.Adapter<AdapterPrincipal.ViewHolder>() {

    class ViewHolder(val binding: PadreItemBinding, val listener2: IListListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun LlenarInfo(pelicula: CollectionPelicula) {
            binding.tvClasificacionPelicula.text = pelicula.titulo
            val movieAdapter = AdapterPelicula(pelicula.peliculaModelo, listener2)
            binding.rvPeliculaHijo.adapter = movieAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PadreItemBinding.inflate(inflater, null, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val peliculaListaCategoria = collectionPelicula[position]
        holder.LlenarInfo(peliculaListaCategoria)
    }

    override fun getItemCount() = collectionPelicula.size

}