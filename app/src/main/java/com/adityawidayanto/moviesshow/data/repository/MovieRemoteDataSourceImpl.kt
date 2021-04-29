package com.adityawidayanto.moviesshow.data.repository

import com.adityawidayanto.moviesshow.data.api.MovieApi
import com.adityawidayanto.moviesshow.data.bean.movie.MovieList
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val movieApi: MovieApi,
    private val apiKey: String
) : MovieRemoteDatasource {
    override suspend fun getMovies(): Response<MovieList> = movieApi.getPopularMovies(apiKey)

}