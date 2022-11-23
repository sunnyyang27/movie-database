package com.e.moviedatabase.ui.popular

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.moviedatabase.R
import com.e.moviedatabase.model.PopularMovie
import com.e.moviedatabase.ui.Helper
import com.e.moviedatabase.ui.details.DetailsActivity
import com.squareup.picasso.Picasso

class PopularMoviesViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val poster: ImageView = view.findViewById(R.id.movie_poster)
    private val title: TextView = view.findViewById(R.id.movie_title)
    private val overview: TextView = view.findViewById(R.id.movie_overview)

    fun bind(popularMovie: PopularMovie) {
        title.text = popularMovie.title
        Picasso.with(this.itemView.context)
            .load(popularMovie.poster_path?.let { Helper.getImagePath(it) })
            .resize(300, 0)
            .into(poster)

        overview.text = popularMovie.overview

        if (popularMovie.id != null) {
            this.itemView.setOnClickListener {
                val intent = Intent(this.itemView.context, DetailsActivity::class.java)
                intent.putExtra("id", popularMovie.id)
                this.itemView.context.startActivity(intent)
            }
        }

    }

    companion object {
        fun create(parent: ViewGroup): PopularMoviesViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.popular_movie_item, parent, false)
            return PopularMoviesViewHolder(view)
        }
    }
}