package com.example.movieapp.repositorio.remoto.manejador

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.example.movieapp.repositorio.api.TmdbApi
import com.example.movieapp.repositorio.remoto.interfaces.RepositorioTmdb
import com.graphqlapollo.PeliculaListaQuery
import javax.inject.Inject

class TmdbManejador @Inject constructor (private val TmdbApi: TmdbApi):
    RepositorioTmdb {
    override suspend fun obtenerListaPelicula(): Response<PeliculaListaQuery.Data> {
        return TmdbApi.obtenerClienteAPollo().query(PeliculaListaQuery()).await()
    }
}