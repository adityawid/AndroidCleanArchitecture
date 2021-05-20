package com.adityawidayanto.movies.view.ui.favorite.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.databinding.ItemMovieBinding
import com.bumptech.glide.Glide

class MoviePagingAdapter :
    PagingDataAdapter<Movie, MoviePagingAdapter.MyViewHolder>(listItemCallback) {
    var onItemClick: ((Movie) -> Unit)? = null

    companion object {
        val listItemCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_movie,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        data?.let { holder.bind(it) }
    }

    inner class MyViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val item = getItem(absoluteAdapterPosition)
                if (item != null) {
                    onItemClick?.invoke(item)
                }
            }
        }

        fun bind(movie: Movie) {
            binding.titleTextView.text = movie.title
            binding.descriptionTextView.text = movie.overview
            val posterURL = "https://image.tmdb.org/t/p/w500" + movie.posterPath
            Glide.with(binding.imageView.context)
                .load(posterURL)
                .into(binding.imageView)

        }


    }
}


