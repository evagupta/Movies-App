package com.backbase.assignment.ui.repository

import com.backbase.assignment.ui.retrofit.RetrofitClient

class Repository constructor(private val retrofitService: RetrofitClient) {

    fun getAllMovies() = retrofitService.getMovies()
    fun getAllPosterImages() = retrofitService.getPosterImages()
    fun getMovieDetails(url : String) = retrofitService.getMovieDetails(url)
}
