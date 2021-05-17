package com.adityawidayanto.movies.data.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.adityawidayanto.db.MovieDao
import com.adityawidayanto.db.entity.Movie

class MovieLocalDataSource(
    private val movieDao: MovieDao,
) {
    fun getFavMovies() = Pager(
        config = PagingConfig(
            10
        )
    )
    { movieDao.getAllFavMovie() }
        .liveData

    suspend fun addFavoriteMovie(movie: Movie) {
        movieDao.insert(movie)
    }

    suspend fun deleteMovieFavorite(movie: Movie) {
        movieDao.deleteFavorite(movie.id)
    }

    suspend fun checkMovieFavorite(movie: Movie): Int = movieDao.checkIdMovie(movie.id)

}