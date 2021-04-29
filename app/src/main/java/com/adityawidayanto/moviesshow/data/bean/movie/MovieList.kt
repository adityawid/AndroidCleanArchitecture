package com.adityawidayanto.moviesshow.data.bean.movie

import com.adityawidayanto.db.bean.Movie
import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results")
    val movies: List<Movie>
)