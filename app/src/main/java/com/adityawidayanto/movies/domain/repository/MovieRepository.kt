package com.adityawidayanto.movies.domain.repository

import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.movies.data.response.MovieListBean

interface MovieRepository {
    suspend fun getPopularMovie(page: Int, pageSize: Int): Result<MovieListBean>

}