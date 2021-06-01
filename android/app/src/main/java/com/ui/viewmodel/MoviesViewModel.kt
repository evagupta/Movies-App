package com.backbase.assignment.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.backbase.assignment.ui.model.MovieDetail
import com.backbase.assignment.ui.model.Movies
import com.backbase.assignment.ui.model.PosterImage
import com.backbase.assignment.ui.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel constructor(private val repository: Repository) : ViewModel() {

    private val mMoviesdata = MutableLiveData<Movies>()
    private val mPosterImagesdata = MutableLiveData<PosterImage>()
    private val mMovieDetailsdata = MutableLiveData<MovieDetail>()
    val errorMessage = MutableLiveData<String>()

    fun getMoviesLiveData(): MutableLiveData<Movies> {
        return mMoviesdata
    }

    fun getPosterImagesData() : MutableLiveData<PosterImage>{
        return mPosterImagesdata
    }

    fun getMovieDetailLiveData() : MutableLiveData<MovieDetail>{
        return mMovieDetailsdata
    }

    fun getAllMovies() {
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                mMoviesdata.value = response.body()
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                errorMessage.value = t.message
            }
        })
    }

    fun getAllPosterImages(){
        val response = repository.getAllPosterImages()
        response.enqueue(object : Callback<PosterImage> {
            override fun onResponse(call: Call<PosterImage>, response: Response<PosterImage>) {
                mPosterImagesdata.value = response.body()
            }

            override fun onFailure(call: Call<PosterImage>, t: Throwable) {
                errorMessage.value = t.message
            }
        })
    }

    fun getMovieDetails(url :String){

        repository.getMovieDetails(url).enqueue(object : retrofit2.Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                mMovieDetailsdata.value = response.body()
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                errorMessage.value = t.message
            }
        })
    }
}