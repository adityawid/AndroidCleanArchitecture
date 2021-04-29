package com.adityawidayanto.moviesshow.domain.repository

import com.adityawidayanto.db.bean.Movie

interface MovieRepository {
    suspend fun getMovies(): List<Movie>?

}