package com.example.movieapp.repositorio.api

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