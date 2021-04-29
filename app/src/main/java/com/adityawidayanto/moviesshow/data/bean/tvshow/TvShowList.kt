package com.adityawidayanto.moviesshow.data.bean.tvshow

import com.adityawidayanto.db.bean.TvShow
import com.google.gson.annotations.SerializedName

data class TvShowList(
    @SerializedName("results")
    val tvShows: List<TvShow>
)