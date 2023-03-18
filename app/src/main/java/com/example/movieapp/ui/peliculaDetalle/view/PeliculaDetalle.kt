package com.example.movieapp.ui.peliculaDetalle.view

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.databinding.ActivityPeliculaDetalleBinding
import com.example.movieapp.repositorio.model.Pelicula
import com.squareup.picasso.Picasso
import java.io.Serializable

class PeliculaDetalle : AppCompatActivity() {
    private lateinit var binding: ActivityPeliculaDetalleBinding
    private var pelicula: Pelicula = Pelicula()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeliculaDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pelicula = getSerializable(this, "pelicula", Pelicula::class.java)
        inicializarInfo()

    }

    private fun inicializarInfo() {
        binding.tvTitulo.text = pelicula.titulo
        val url = pelicula.poster
        Picasso.get()
            .load(url)
            .into(binding.ivPoster)
        binding.tvDescripcion.text = pelicula.descripcion
        binding.tvCalificacion.text = pelicula.clasificacion.toString()
        binding.tvPresupuesto.text = pelicula.presupuesto.toString()
    }


    private fun <T : Serializable?> getSerializable(
        activity: Activity,
        name: String,
        clazz: Class<T>
    ): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            activity.intent.getSerializableExtra(name, clazz)!!
        else
            activity.intent.getSerializableExtra(name) as T
    }
}