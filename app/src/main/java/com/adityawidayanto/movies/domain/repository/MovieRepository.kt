package com.adityawidayanto.movies.domain.repository

import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.db.entity.Movie

interface MovieRepository {
    suspend fun getPopularMovie(page: Int, pageSize: Int): Result<List<Movie>>

}