package com.adityawidayanto.movies.data.response

import com.adityawidayanto.db.entity.Movie
import com.google.gson.annotations.SerializedName

data class MovieListBean(
    @SerializedName("results")
    val movies: List<Movie>
)