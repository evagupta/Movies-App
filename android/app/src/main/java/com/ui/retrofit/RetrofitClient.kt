package com.backbase.assignment.ui.retrofit

import com.backbase.assignment.ui.model.MovieDetail
import com.backbase.assignment.ui.model.Movies
import com.backbase.assignment.ui.model.PosterImage
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitClient {

    @GET("movie/popular?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US&page=1")
    fun getMovies() : Call<Movies>

    @GET("movie/now_playing?language=en-US&page=undefined&api_key=55957fcf3ba81b137f8fc01ac5a31fb5")
    fun getPosterImages() : Call<PosterImage>

    @GET
    fun getMovieDetails(@Url path : String) : Call<MovieDetail>


    companion object {

        var retrofitService: RetrofitClient? = null
        private val client = OkHttpClient.Builder().build()

        fun getInstance() : RetrofitClient {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(RetrofitClient::class.java)
            }
            return retrofitService!!
        }
    }
}