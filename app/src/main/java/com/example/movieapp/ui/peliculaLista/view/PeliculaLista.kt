package com.example.movieapp.ui.peliculaLista.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.repositorio.model.Pelicula
import com.example.movieapp.repositorio.model.CollectionPelicula
import com.example.movieapp.ui.peliculaDetalle.view.PeliculaDetalle
import com.example.movieapp.ui.peliculaLista.adapter.AdapterPrincipal
import com.example.movieapp.ui.peliculaLista.viewModel.PeliculaListaViewModel
import com.example.movieapp.utils.CargandoDialog
import com.example.movieapp.utils.IListListener
import com.example.movieapp.utils.ViewState
import com.graphqlapollo.PeliculaListaQuery
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class PeliculaLista : AppCompatActivity(), IListListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PeliculaListaViewModel by viewModels()
    private lateinit var peliculaLista: PeliculaListaQuery.Movies
    private var nowPlayingMovieList: ArrayList<Pelicula> = arrayListOf()
    private var trendingMovieList: ArrayList<Pelicula> = arrayListOf()
    private var topRatedList: ArrayList<Pelicula> = arrayListOf()
    private var popularMovieList: ArrayList<Pelicula> = arrayListOf()
    private var upcomingMovieList: ArrayList<Pelicula> = arrayListOf()
    private lateinit var loading: CargandoDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.queryPeliculaLista()
        observarInfo()
        loading = CargandoDialog(this)
    }

    private fun observarInfo() {
        viewModel.PeliculaLista.observe(this) { response ->
            when (response) {
                is ViewState.Success -> {
                    val results = response.value?.data?.movies
                    if (results != null) {
                        peliculaLista = results
                        crearListaPelicula()
                    }
                    loading.isDismiss()
                }
                is ViewState.Error -> {
                    loading.isDismiss()
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    loading.startLoading()
                }
            }
        }
    }

    private fun crearListaPelicula() {
        //now playing
        for (i in peliculaLista.nowPlaying.edges!!) {
            val pelicula = i?.node?.id?.let {
                Pelicula(
                    i.node.id,
                    i.node.title,
                    i.node.poster.toString(),
                    i.node.overview,
                    i.node.rating,
                    i.node.budget
                )
            }
            if (pelicula != null) {
                nowPlayingMovieList.add(pelicula)
            }
        }
        //trending
        for (i in peliculaLista.trending.edges!!) {
            val pelicula = i?.node?.id?.let {
                Pelicula(
                    i.node.id,
                    i.node.title,
                    i.node.poster.toString(),
                    i.node.overview,
                    i.node.rating,
                    i.node.budget
                )
            }
            if (pelicula != null) {
                trendingMovieList.add(pelicula)
            }
        }
        //popular
        for (i in peliculaLista.popular.edges!!) {
            val pelicula = i?.node?.id?.let {
                Pelicula(
                    i.node.id,
                    i.node.title,
                    i.node.poster.toString(),
                    i.node.overview,
                    i.node.rating,
                    i.node.budget
                )
            }
            if (pelicula != null) {
                popularMovieList.add(pelicula)
            }
        }
        //topRated
        for (i in peliculaLista.topRated.edges!!) {
            val pelicula = i?.node?.id?.let {
                Pelicula(
                    i.node.id,
                    i.node.title,
                    i.node.poster.toString(),
                    i.node.overview,
                    i.node.rating,
                    i.node.budget
                )
            }
            if (pelicula != null) {
                topRatedList.add(pelicula)
            }
        }
        //upcoming
        for (i in peliculaLista.upcoming.edges!!) {
            val pelicula = i?.node?.id?.let {
                Pelicula(
                    i.node.id,
                    i.node.title,
                    i.node.poster.toString(),
                    i.node.overview,
                    i.node.rating,
                    i.node.budget
                )
            }
            if (pelicula != null) {
                upcomingMovieList.add(pelicula)
            }
        }

        val peliculaListaFinal = listOf(
            CollectionPelicula(getString(R.string.now_playing), nowPlayingMovieList),
            CollectionPelicula(getString(R.string.trending), trendingMovieList),
            CollectionPelicula(getString(R.string.popular), popularMovieList),
            CollectionPelicula(getString(R.string.top_rated), topRatedList),
            CollectionPelicula(getString(R.string.upcoming), upcomingMovieList),
        )

        binding.rv.adapter = AdapterPrincipal(peliculaListaFinal, this)

    }

    override fun detallePelicula(pelicula: Pelicula) {

        val intent = Intent(this, PeliculaDetalle::class.java)
        intent.putExtra("pelicula", pelicula as Serializable)
        startActivity(intent)
    }

}