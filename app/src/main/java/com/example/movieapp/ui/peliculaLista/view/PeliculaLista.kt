package com.example.movieapp.ui.peliculaLista.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.repositorio.model.Pelicula
import com.example.movieapp.ui.peliculaDetalle.view.PeliculaDetalle
import com.example.movieapp.ui.peliculaLista.adapter.PeliculaListaAdaptador
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
    private lateinit var adaptadorNowPlaying: PeliculaListaAdaptador
    private lateinit var adaptadorTrending: PeliculaListaAdaptador
    private lateinit var adaptadorTopRated: PeliculaListaAdaptador
    private lateinit var adaptadorPopular: PeliculaListaAdaptador
    private lateinit var adaptadorUpcoming: PeliculaListaAdaptador
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
        inicializarLista()
    }

    private fun inicializarLista() {
        adaptadorNowPlaying = PeliculaListaAdaptador(nowPlayingMovieList, this)
        adaptadorTrending = PeliculaListaAdaptador(trendingMovieList, this)
        adaptadorPopular = PeliculaListaAdaptador(popularMovieList, this)
        adaptadorTopRated = PeliculaListaAdaptador(topRatedList, this)
        adaptadorUpcoming = PeliculaListaAdaptador(upcomingMovieList, this)
        setearRecycler()
    }

    private fun setearRecycler() {
        //Now playing
        val linearLayoutManagerNowPlaying =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvNowPlaying.adapter = adaptadorNowPlaying
        binding.rvNowPlaying.layoutManager = linearLayoutManagerNowPlaying
        val snapHelperNowPlaying = LinearSnapHelper()
        snapHelperNowPlaying.attachToRecyclerView(binding.rvNowPlaying)
        binding.tvNowPlaying.visibility = View.VISIBLE

        //trending
        val linearLayoutManagerTrending =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTrending.adapter = adaptadorTrending
        binding.rvTrending.layoutManager = linearLayoutManagerTrending
        val snapHelperTrending = LinearSnapHelper()
        snapHelperTrending.attachToRecyclerView(binding.rvTrending)
        binding.tvTrending.visibility = View.VISIBLE

        //popular
        val linearLayoutManagerPopular =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopular.adapter = adaptadorPopular
        binding.rvPopular.layoutManager = linearLayoutManagerPopular
        val snapHelperPopular = LinearSnapHelper()
        snapHelperPopular.attachToRecyclerView(binding.rvPopular)
        binding.tvPopular.visibility = View.VISIBLE

        //topRated
        val linearLayoutManagerTopRated =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTopRated.adapter = adaptadorTopRated
        binding.rvTopRated.layoutManager = linearLayoutManagerTopRated
        val snapHelperTopRated = LinearSnapHelper()
        snapHelperTopRated.attachToRecyclerView(binding.rvTopRated)
        binding.tvTopRated.visibility = View.VISIBLE

        //upcoming
        val linearLayoutManagerUpcoming =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvUpcoming.adapter = adaptadorUpcoming
        binding.rvUpcoming.layoutManager = linearLayoutManagerUpcoming
        val snapHelperUpcoming = LinearSnapHelper()
        snapHelperUpcoming.attachToRecyclerView(binding.rvUpcoming)
        binding.tvUpcoming.visibility = View.VISIBLE
    }

    override fun detallePelicula(pelicula: Pelicula) {
        val intent = Intent(this, PeliculaDetalle::class.java)
        intent.putExtra("pelicula", pelicula as Serializable)
        startActivity(intent)
    }

}