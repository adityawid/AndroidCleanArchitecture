package com.adityawidayanto.moviesshow.view.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.adityawidayanto.db.bean.Movie
import com.adityawidayanto.moviesshow.R
import com.adityawidayanto.moviesshow.databinding.ItemMovieBinding
import com.bumptech.glide.Glide

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
    private val movieList = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        movieList.clear()
        movieList.addAll(newListData)
        notifyDataSetChanged()
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
        val data = movieList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = movieList.size

    inner class MyViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(movieList[adapterPosition])
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


