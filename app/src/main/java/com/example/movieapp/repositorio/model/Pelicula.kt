package com.example.movieapp.repositorio.model

import java.io.Serializable

data class Pelicula(
    val id: String? = null,
    val titulo: String? = null,
    val poster: String? = null,
    val descripcion: String? = null,
    val clasificacion: Double? = null,
    val presupuesto: Int? = null
) : Serializable