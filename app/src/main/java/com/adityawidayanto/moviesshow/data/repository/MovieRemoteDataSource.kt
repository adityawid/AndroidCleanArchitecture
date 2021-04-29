package com.adityawidayanto.moviesshow.data.repository

import com.adityawidayanto.moviesshow.data.bean.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies(): Response<MovieList>
}