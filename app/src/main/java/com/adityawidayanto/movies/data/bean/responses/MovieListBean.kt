package com.adityawidayanto.movies.data.bean.responses

import com.adityawidayanto.db.entity.Movie
import com.google.gson.annotations.SerializedName

data class MovieListBean(
    @SerializedName("results")
    val movies: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("page")
    val page: Int
)