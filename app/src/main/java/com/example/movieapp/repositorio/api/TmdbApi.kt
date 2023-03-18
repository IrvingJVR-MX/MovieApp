package com.example.movieapp.repositorio.api

import android.os.Looper
import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

class TmdbApi {
    fun obtenerClienteAPollo(): ApolloClient {
        check(Looper.myLooper() == Looper.getMainLooper()) {
            "Solo el hilo principal puede obtener la instancia de apolloClient"
        }
        val okHttpClient = OkHttpClient.Builder().build()
        return ApolloClient.builder()
            .serverUrl("https://tmdb.apps.quintero.io/")
            .okHttpClient(okHttpClient)
            .build()
    }
}