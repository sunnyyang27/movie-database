package com.e.moviedatabase.api

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "aedcdf906de72c38df081d7f7a43ae03"

interface MovieApi {
    @GET("popular")
    fun popularMoviesList(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY,
    ): Call<PopularMoviesResponse>

    @GET("{id}")
    fun movieDetails(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = API_KEY,
    ): Call<MovieDetailResponse>

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        private var INSTANCE: MovieApi? = null

        fun getApi(): MovieApi {
            if (INSTANCE == null) {
                INSTANCE = create()
            }
            return INSTANCE as MovieApi
        }

        private fun create(): MovieApi {
            val client = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi::class.java)
        }

    }
}