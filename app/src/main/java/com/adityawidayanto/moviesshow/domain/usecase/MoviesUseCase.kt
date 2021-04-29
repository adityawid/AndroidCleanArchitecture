package com.adityawidayanto.moviesshow.domain.usecase

import com.adityawidayanto.db.bean.Movie
import com.adityawidayanto.moviesshow.domain.repository.MovieRepository

class MoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.getMovies()
}