package com.adityawidayanto.moviesshow.domain.repository

import com.adityawidayanto.db.bean.TvShow

interface TvShowRepository {
    suspend fun getTvShows(): List<TvShow>?
}