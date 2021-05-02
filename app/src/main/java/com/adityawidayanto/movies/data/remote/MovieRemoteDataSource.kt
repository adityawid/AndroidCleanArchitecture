package com.adityawidayanto.movies.data.remote

import com.adityawidayanto.core.data.remote.RemoteDataSource
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.movies.data.MovieApi
import com.adityawidayanto.movies.data.response.MovieListBean
import kotlinx.coroutines.CoroutineDispatcher

class MovieRemoteDataSource(private val movieApi: MovieApi, private val apiKey: String) :
    RemoteDataSource() {

    suspend fun getPopularMovies(
        dispatcher: CoroutineDispatcher,
        page: Int,
        pageSize: Int
    ): Result<MovieListBean> {
        return safeApiCall(dispatcher) {
            movieApi.getPopularMovies(apiKey)
        }
    }

}