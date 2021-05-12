package com.adityawidayanto.movies.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.bean.responses.MovieListBean
import com.adityawidayanto.movies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(): Result<MovieListBean> {
        return repository.getPopularMovie(1, 10)
    }

    fun getPagingMovie(): LiveData<PagingData<Movie>> {
        return repository.getPagingPopularMovie()
    }

    suspend fun addMovieFavorite(movie: Movie) {
        repository.addMovieFavorite(movie)
    }

    suspend fun removeMovieFavorite(movie: Movie) {
        repository.deleteMovieFavorite(movie)
    }

    suspend fun checkMovieFavorite(movie: Movie): Int =
        repository.checkMovieFavorite(movie)
}