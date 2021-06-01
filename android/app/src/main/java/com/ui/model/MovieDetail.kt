package com.backbase.assignment.ui.model

import com.google.gson.annotations.SerializedName

class MovieDetail {

    @SerializedName("poster_path")
    val poster_path: String? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("overview")
    val overview: String? = null

    @SerializedName("release_date")
    val release_date: String? = null

    @SerializedName("genres")
    val genres: List<Genres>? = null

    class Genres{

        @SerializedName("id")
        val id: String? = null

        @SerializedName("name")
        val name: String? = null

    }
}