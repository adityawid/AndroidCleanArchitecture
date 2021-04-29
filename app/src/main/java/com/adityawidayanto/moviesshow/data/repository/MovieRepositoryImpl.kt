package com.adityawidayanto.moviesshow.data.repository

import android.util.Log
import com.adityawidayanto.db.bean.Movie
import com.adityawidayanto.moviesshow.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieRemoteDatasource: MovieRemoteDatasource,
) : MovieRepository {
    override suspend fun getMovies(): List<Movie>? = getMoviesFromApi()

    private suspend fun getMoviesFromApi(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            val response = movieRemoteDatasource.getMovies()
            val body = response.body()
            if (body != null) {
                movieList = body.movies
            }
        } catch (exception: Exception) {
            movieList = emptyList()
            Log.e("MovieRepositoryImpl", exception.message.toString())
        }
        return movieList
    }
}