package com.e.moviedatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.moviedatabase.api.MovieApi
import com.e.moviedatabase.api.PopularMoviesResponse
import com.e.moviedatabase.model.PopularMovie
import com.e.moviedatabase.ui.popular.PopularMoviesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: PopularMoviesAdapter
    private lateinit var recyclerView: RecyclerView
    private var page: Int = 1
    private var dataLoading: Boolean = false
    private var moviesList = ArrayList<PopularMovie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        adapter = PopularMoviesAdapter()
        val layoutManager = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.movies_list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        addScrollListener()
        getMovies()
    }

    private fun addScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!dataLoading) {
                    if (linearLayoutManager != null
                        && linearLayoutManager.findLastCompletelyVisibleItemPosition() == moviesList.size - 1) {
                        page += 1
                        getMovies()
                        dataLoading = true
                    }
                }
            }
        })
    }

    private fun getMovies() {
        val api = MovieApi.getApi()
        api.popularMoviesList(this.page).enqueue(object : Callback<PopularMoviesResponse> {
            override fun onResponse(
                call: Call<PopularMoviesResponse>,
                response: Response<PopularMoviesResponse>
            ) {
                val movies = response.body()?.results
                moviesList.addAll(movies as MutableList<PopularMovie>)
                adapter.submitList(moviesList)
                adapter.notifyDataSetChanged()
                dataLoading = false
            }
            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {}
        })
    }
}