package com.adityawidayanto.moviesshow.data.repository

import com.adityawidayanto.moviesshow.data.bean.movie.MovieList
import retrofit2.Response

interface MovieRemoteDatasource {
    suspend fun getMovies(): Response<MovieList>
}