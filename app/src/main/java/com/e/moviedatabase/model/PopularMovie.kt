package com.e.moviedatabase.model

import com.google.gson.annotations.SerializedName

data class PopularMovie (
    @field:SerializedName("id") val id: Int?,
    @field:SerializedName("overview") val overview: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("poster_path") val poster_path: String?,
    @field:SerializedName("vote_average") val vote_average: Float,
)