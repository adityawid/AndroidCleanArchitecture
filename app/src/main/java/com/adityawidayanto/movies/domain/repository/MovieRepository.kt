package com.adityawidayanto.movies.domain.repository

import androidx.paging.DataSource
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.bean.responses.MovieListBean

interface MovieRepository {
    suspend fun getPopularMovie(page: Int, pageSize: Int): Result<MovieListBean>
    suspend fun insertMovieFavorite(movie: Movie)
    fun findAllMovieFavorite(): DataSource.Factory<Int, Movie>
}