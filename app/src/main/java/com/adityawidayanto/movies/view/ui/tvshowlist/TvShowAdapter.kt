package com.adityawidayanto.movies.view.ui.tvshowlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.databinding.ItemMovieBinding
import com.bumptech.glide.Glide

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.MyViewHolder>() {
    private val tvShowList = ArrayList<TvShow>()
    var onItemClick: ((TvShow) -> Unit)? = null

    fun setData(newListData: List<TvShow>?) {
        if (newListData == null) return
        tvShowList.clear()
        tvShowList.addAll(newListData)
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
        val data = tvShowList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = tvShowList.size

    inner class MyViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(tvShowList[adapterPosition])
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


