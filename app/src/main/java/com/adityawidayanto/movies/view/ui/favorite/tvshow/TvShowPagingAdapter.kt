package com.adityawidayanto.movies.view.ui.favorite.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.databinding.ItemMovieBinding
import com.bumptech.glide.Glide

class TvShowPagingAdapter :
    PagingDataAdapter<TvShow, TvShowPagingAdapter.MyViewHolder>(ListItemCallback()) {
    var onItemClick: ((TvShow) -> Unit)? = null

    private class ListItemCallback : DiffUtil.ItemCallback<TvShow>() {
        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem == newItem
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

        fun bind(tvShow: TvShow) {
            binding.titleTextView.text = tvShow.name
            binding.descriptionTextView.text = tvShow.overview
            val posterURL = "https://image.tmdb.org/t/p/w500" + tvShow.posterPath
            Glide.with(binding.imageView.context)
                .load(posterURL)
                .into(binding.imageView)

        }


    }
}


