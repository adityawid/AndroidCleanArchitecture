package com.adityawidayanto.movies.domain.usecase

import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.movies.data.response.MovieListBean
import com.adityawidayanto.movies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(): Result<MovieListBean> {
        return repository.getPopularMovie(1, 10)
    }
}