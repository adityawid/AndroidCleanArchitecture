package com.adityawidayanto.movies.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.bean.responses.MovieListBean

interface MovieRepository {
    suspend fun getPopularMovie(page: Int, pageSize: Int): Result<MovieListBean>
    fun getPagingPopularMovie(): LiveData<PagingData<Movie>>
    suspend fun addMovieFavorite(movie: Movie)
    suspend fun deleteMovieFavorite(movie: Movie)
    suspend fun checkMovieFavorite(movie: Movie): Int
}