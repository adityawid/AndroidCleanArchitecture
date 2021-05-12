package com.adityawidayanto.movies.data.repository.movie

import androidx.lifecycle.LiveData
import com.adityawidayanto.db.MovieDao
import com.adityawidayanto.db.entity.Movie

class MovieLocalDataSource(private val movieDao: MovieDao) {

    suspend fun getFavoriteMovies(): LiveData<Movie> = movieDao.findAll()
    suspend fun addFavoriteMovie(movie: Movie) {
        movieDao.insert(movie)
    }

    suspend fun deleteMovieFavorite(movie: Movie) {
        movieDao.deleteFavorite(movie.id)
    }

    suspend fun checkMovieFavorite(movie: Movie): Int = movieDao.checkIdMovie(movie.id)

}