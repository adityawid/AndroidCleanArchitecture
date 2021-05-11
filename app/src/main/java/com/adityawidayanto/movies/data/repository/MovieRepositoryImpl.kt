package com.adityawidayanto.movies.data.repository

import androidx.paging.DataSource
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.core.utils.dispatcher.DispatcherProvider
import com.adityawidayanto.db.dao.MovieDao
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.bean.responses.MovieListBean
import com.adityawidayanto.movies.data.repository.movie.MovieRemoteDataSource
import com.adityawidayanto.movies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val remoteDataSource: MovieRemoteDataSource,
    private val dao: MovieDao
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

    override suspend fun insertMovieFavorite(movie: Movie) {
        dao.insert(movie)
    }

    override fun findAllMovieFavorite(): DataSource.Factory<Int, Movie> {
        return dao.findAll()
    }
}