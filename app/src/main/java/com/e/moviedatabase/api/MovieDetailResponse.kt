package com.e.moviedatabase.api

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse (
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("vote_average") val vote_average: Float
)