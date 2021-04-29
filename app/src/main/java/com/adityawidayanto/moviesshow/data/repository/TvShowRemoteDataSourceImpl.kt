package com.adityawidayanto.moviesshow.data.repository

import com.adityawidayanto.moviesshow.data.api.MovieApi
import com.adityawidayanto.moviesshow.data.bean.tvshow.TvShowList
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val tmdbApi: MovieApi,
    private val apiKey: String
) : TvShowRemoteDataSource {

    override suspend fun getTvShow(): Response<TvShowList> = tmdbApi.getPopularTvShows(apiKey)

}