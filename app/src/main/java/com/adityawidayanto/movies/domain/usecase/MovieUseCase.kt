package com.adityawidayanto.movies.domain.usecase

import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(): Result<List<Movie>> {
        return repository.getPopularMovie(1, 10)
    }

    fun get(): List<Movie> = emptyList()

}