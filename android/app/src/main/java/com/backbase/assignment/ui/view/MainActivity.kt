package com.backbase.assignment.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.ui.custom.ItemDivider
import com.backbase.assignment.ui.movie.MoviesAdapter
import com.backbase.assignment.ui.movie.PicturesAdapter
import com.backbase.assignment.ui.repository.Repository
import com.backbase.assignment.ui.retrofit.RetrofitClient
import com.backbase.assignment.ui.viewmodel.MoviesViewModel
import com.backbase.assignment.ui.viewmodel.ViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var picturesAdapter: PicturesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var poster_images_recyclerView: RecyclerView
    lateinit var mMoviesViewModel: MoviesViewModel
    private val retrofitService = RetrofitClient.getInstance()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Vertical recyclerview to show the list of movies
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration( ItemDivider(this, 2, 2)) // For divider decoration

        // Horizontal recyclerview to show the posters of movies
        poster_images_recyclerView = findViewById(R.id.poster_images)
        poster_images_recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Initializing the viewModel
        mMoviesViewModel =
            ViewModelProvider(this, ViewModelFactory(Repository(retrofitService))).get(
                MoviesViewModel::class.java
            )
        // Making call to the viewModel API methods to fetch the data
        mMoviesViewModel.getAllMovies()
        mMoviesViewModel.getAllPosterImages()

        // To get the response of the API calls
        fetchMovies()
        fetchPosterImages()
    }

    /* This fun is getting the Live Data from the viewModel for Movies
    * Once the data is received it is passed to adapter to set the view*/
    private fun fetchMovies() {
        mMoviesViewModel.getMoviesLiveData().observe(this, Observer {
            moviesAdapter = MoviesAdapter(this, it.results)
            recyclerView.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()
        })
    }
    /* This fun is getting the Live Data from the viewModel for poster images of movies
       * Once the data is received it is passed to adapter to set the view*/
    private fun fetchPosterImages() {
        mMoviesViewModel.getPosterImagesData().observe(this, Observer {
            picturesAdapter = PicturesAdapter(this, it.results)
            poster_images_recyclerView.adapter = picturesAdapter
            picturesAdapter.notifyDataSetChanged()
        })
    }
}
