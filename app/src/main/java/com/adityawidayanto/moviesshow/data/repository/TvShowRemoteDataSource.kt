package com.adityawidayanto.moviesshow.data.repository

import com.adityawidayanto.moviesshow.data.bean.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShow(): Response<TvShowList>
}