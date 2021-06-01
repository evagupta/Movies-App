package com.backbase.assignment.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.backbase.assignment.R
import com.backbase.assignment.ui.repository.Repository
import com.backbase.assignment.ui.retrofit.RetrofitClient
import com.backbase.assignment.ui.viewmodel.MoviesViewModel
import com.backbase.assignment.ui.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide


class MoviesDetailActivity : AppCompatActivity() {

    lateinit var mMoviesViewModel: MoviesViewModel
    private val retrofitService = RetrofitClient.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_detail)

        // Initializing the viewModel
        mMoviesViewModel =
            ViewModelProvider(this, ViewModelFactory(Repository(retrofitService))).get(
                MoviesViewModel::class.java
            )

        // Id is passed from MoviesAdpater on the click of an item
        val id = intent.getStringExtra("id")
        // Url to get the movie details by adding the required "id"
        val url =
            "https://api.themoviedb.org/3/movie/$id?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US"

        // Making call to the viewModel API methods to fetch the data
        mMoviesViewModel.getMovieDetails(url)

        // Setting the view
        setUi()
    }

    fun setUi() {
        val poster = findViewById(R.id.poster) as? ImageView
        var duration = findViewById(R.id.duration) as? TextView
        val title = findViewById(R.id.title) as? TextView
        val overview = findViewById(R.id.overview) as? TextView
        val releaseDate = findViewById(R.id.releaseDate) as? TextView

        /* This fun is getting the Live Data from the viewModel for MovieDetails
         * Once the data is received it is set to the views*/
        mMoviesViewModel.getMovieDetailLiveData().observe(this, Observer {
            title?.text = it.title
            overview?.text = it.overview
            releaseDate?.text = it.release_date
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/original/${it.poster_path}")
                .into(poster)

            //Dynamic views created for the genres which vary with each movie item
            for (i in it.genres?.indices!!) {
                val vi = applicationContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val insertPoint = findViewById<View>(R.id.custom_view) as ViewGroup
                val v: View = vi.inflate(R.layout.genre_item, insertPoint, false)

                v.findViewById<TextView>(R.id.txt_genres).text = it.genres[i].name

                insertPoint.addView(
                    v,
                    0,
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                )
            }
        })
    }
}