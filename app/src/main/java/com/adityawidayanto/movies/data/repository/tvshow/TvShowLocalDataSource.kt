package com.adityawidayanto.movies.data.repository.tvshow

import com.adityawidayanto.core.data.remote.RemoteDataSource
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.movies.data.api.MovieApi
import com.adityawidayanto.movies.data.bean.responses.TvShowListBean
import kotlinx.coroutines.CoroutineDispatcher

class TvShowLocalDataSource(private val movieApi: MovieApi, private val apiKey: String) :
    RemoteDataSource() {
    suspend fun getPopularTvShow(
        dispatcher: CoroutineDispatcher,
        page: Int,
        pageSize: Int
    ): Result<TvShowListBean> {
        return safeApiCall(dispatcher) {
            movieApi.getPopularTvShows(apiKey)
        }
    }

}