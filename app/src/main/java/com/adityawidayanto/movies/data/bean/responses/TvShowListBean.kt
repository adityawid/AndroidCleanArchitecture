package com.adityawidayanto.movies.data.bean.responses

import com.adityawidayanto.db.entity.TvShow
import com.google.gson.annotations.SerializedName

data class TvShowListBean(
    @SerializedName("results")
    val tvShows: List<TvShow>
)