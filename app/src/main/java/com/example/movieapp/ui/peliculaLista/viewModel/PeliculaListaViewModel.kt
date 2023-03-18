package com.example.movieapp.ui.peliculaLista.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.repositorio.remoto.interfaces.RepositorioTmdb
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.graphqlapollo.PeliculaListaQuery
import com.example.movieapp.utils.ViewState
import kotlinx.coroutines.launch

@HiltViewModel
class PeliculaListaViewModel
@Inject constructor(private val repositorio: RepositorioTmdb) : ViewModel() {
    private val _PeliculaLista by lazy { MutableLiveData<ViewState<Response<PeliculaListaQuery.Data>>>() }
    val PeliculaLista: LiveData<ViewState<Response<PeliculaListaQuery.Data>>> get() = _PeliculaLista
    fun queryPeliculaLista() = viewModelScope.launch {
        _PeliculaLista.postValue(ViewState.Loading())
        try {
            val response = repositorio.obtenerListaPelicula()
            _PeliculaLista.postValue(ViewState.Success(response))
        } catch (e: ApolloException) {
            _PeliculaLista.postValue(ViewState.Error("Error"))
        }
    }
}