package com.example.movieapp.ui.peliculaLista.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.MovieitemBinding
import com.example.movieapp.repositorio.model.Movie
import com.squareup.picasso.Picasso

class MovieListAdapter (private val list: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {


    //the class is hodling the list view
    class ViewHolder(val binding: MovieitemBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun fillData(movie: Movie) {
            val url = movie.poster
            Picasso.get()
                .load(url)
                .into(binding.ivPokemon)
        }
    }

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieitemBinding.inflate(inflater, null, false)
        return ViewHolder(binding)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val movie = list[position]
        holder.fillData(movie)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }
}