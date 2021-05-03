package com.adityawidayanto.movies.data.remote.movie

import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.core.utils.dispatcher.DispatcherProvider
import com.adityawidayanto.movies.data.response.MovieListBean
import com.adityawidayanto.movies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getPopularMovie(
        page: Int,
        pageSize: Int
    ): Result<MovieListBean> {
        return when (val apiResult =
            remoteDataSource.getPopularMovies(dispatcher.io, page, pageSize)) {
            is Result.Loading -> Result.Loading
            is Result.Success -> {
                Result.Success(apiResult.data)
            }
            is Result.Error -> Result.Error(apiResult.cause, apiResult.code, apiResult.errorMessage)
        }

    }
}