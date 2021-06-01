package com.backbase.assignment.ui.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class PosterImage : Serializable {

    @SerializedName("results")
    val results: List<Results>? = null

    class Results {
        @SerializedName("poster_path")
        val poster_path: String? = null
    }
}