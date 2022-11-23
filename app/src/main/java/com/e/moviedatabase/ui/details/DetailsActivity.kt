package com.e.moviedatabase.ui.details

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.e.moviedatabase.R
import com.e.moviedatabase.api.MovieApi
import com.e.moviedatabase.api.MovieDetailResponse
import com.e.moviedatabase.ui.Helper
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra("id", -1)
        if (id >= 0) {
            getMovieDetails(id)
        }
    }

    private fun getMovieDetails(id: Int) {
        val api = MovieApi.getApi()
        api.movieDetails(id).enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                val details = response.body()
                if (details != null) {
                    populateDetails(details)
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {}
        })
    }

    private fun populateDetails(details: MovieDetailResponse) {
        val backdrop: ImageView = findViewById(R.id.backdrop)
        val poster: ImageView = findViewById(R.id.poster)
        val title: TextView = findViewById(R.id.title)
        val overview: TextView = findViewById(R.id.overview)
        val moreInfo: TextView = findViewById(R.id.more_info)
        val rating: TextView = findViewById(R.id.rating)

        Picasso.with(this)
            .load(Helper.getImagePath(details.backdrop_path))
            .into(backdrop)

        Picasso.with(this)
            .load(Helper.getImagePath(details.poster_path))
            .resize(400, 0)
            .into(poster)

        rating.text = String.format("%.1f", details.vote_average)

        title.text = details.title

        overview.text = details.overview

        val sourceDateFormat = SimpleDateFormat("yyyy-mm-dd")
        val releaseDate = sourceDateFormat.parse(details.release_date)
        val targetDateFormat = SimpleDateFormat("MMM dd, yyyy")

        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.setMaximumFractionDigits(0)
        format.setCurrency(Currency.getInstance("USD"))

        moreInfo.text = "${targetDateFormat.format(releaseDate!!)} • ${format.format(details.revenue)} revenue"
    }
}