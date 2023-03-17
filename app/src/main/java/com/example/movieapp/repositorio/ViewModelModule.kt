package com.example.movieapp.repositorio

import com.example.movieapp.repositorio.remoto.interfaces.RepositorioTmdb
import com.example.movieapp.repositorio.remoto.manejador.TmdbManejador
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    @ViewModelScoped
    abstract fun bindRepository(repo: TmdbManejador): RepositorioTmdb
}
