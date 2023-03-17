package com.example.movieapp.repositorio.remoto.interfaces

import com.graphqlapollo.PeliculaListaQuery
import com.apollographql.apollo.api.Response

interface RepositorioTmdb {
  suspend fun obtenerListaPelicula() : Response<PeliculaListaQuery.Data>
}