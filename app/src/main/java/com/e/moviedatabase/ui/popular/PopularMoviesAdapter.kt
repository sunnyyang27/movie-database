package com.e.moviedatabase.ui.popular

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.e.moviedatabase.model.PopularMovie

class PopularMoviesAdapter : ListAdapter<PopularMovie, PopularMoviesViewHolder>(MOVIE_DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder =
        PopularMoviesViewHolder.create(parent)

    companion object {
        private val MOVIE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<PopularMovie>() {
            override fun areItemsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean =
            oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean =
                oldItem == newItem
        }
    }
}