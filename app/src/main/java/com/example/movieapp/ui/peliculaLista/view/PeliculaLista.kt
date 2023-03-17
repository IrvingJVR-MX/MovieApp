package com.example.movieapp.ui.peliculaLista.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.repositorio.model.Movie
import com.example.movieapp.ui.peliculaLista.adapter.MovieListAdapter
import com.example.movieapp.ui.peliculaLista.viewModel.PeliculaListaViewModel
import com.example.movieapp.utils.ViewState
import com.graphqlapollo.PeliculaListaQuery
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeliculaLista : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PeliculaListaViewModel by viewModels()
    private lateinit var peliculaLista: PeliculaListaQuery.Movies
    private lateinit var adapter: MovieListAdapter
    private var list : ArrayList<Movie> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.queryPeliculaLista()
        observeData()
    }

    private fun observeData() {
        viewModel.PeliculaLista.observe(this) {response ->
            when(response) {
                is ViewState.Success ->{
                    val results = response.value?.data?.movies
                    if (results != null) {
                        peliculaLista = results
                        makeMovieList()
                    }
                }
                is ViewState.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
                else -> {
                }
            }
        }
    }

    private fun makeMovieList() {
        for (item in peliculaLista.popular.edges!!) {
            val movie = Movie(item?.node?.id,item?.node?.title,item?.node?.poster.toString())
            list.add(movie)
        }
        initList()
    }


    private fun initList(){
        adapter = MovieListAdapter(list)
        setRecycler()
    }

    private fun setRecycler(){
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = linearLayoutManager
    }


}