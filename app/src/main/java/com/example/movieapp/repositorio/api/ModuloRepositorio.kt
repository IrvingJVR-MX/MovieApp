package com.example.movieapp.repositorio.api

import com.example.movieapp.repositorio.api.TmdbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuloRepositorio {
    @Provides
    @Singleton
    fun provedorTmbdApi() = TmdbApi()
}