package com.backbase.assignment.ui.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Movies : Serializable {

    @SerializedName("results")
    val results: List<Results>? = null

    @SerializedName("page")
    val page: String? = null

    class Results {
        @SerializedName("id")
        val id: String? = null

        @SerializedName("poster_path")
        val poster_path: String? = null

        @SerializedName("title")
        val title: String? = null

        @SerializedName("vote_average")
        val rating: String? = null

        @SerializedName("release_date")
        val release_date: String? = null
    }
}