package com.example.movieapp.utils

import com.example.movieapp.repositorio.model.Pelicula

interface IListListener {
    fun detallePelicula(pelicula: Pelicula)
}