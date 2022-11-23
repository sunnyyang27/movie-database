package com.e.moviedatabase.api

import com.e.moviedatabase.model.PopularMovie
import com.google.gson.annotations.SerializedName
import java.util.Collections.emptyList

data class PopularMoviesResponse (
    @SerializedName("results") val results: List<PopularMovie> = emptyList(),
    @SerializedName("page") val page: Int = 0
)