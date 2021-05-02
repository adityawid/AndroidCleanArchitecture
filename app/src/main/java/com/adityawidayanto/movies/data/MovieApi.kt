package com.adityawidayanto.movies.data

import com.adityawidayanto.movies.data.response.MovieListBean
import com.adityawidayanto.movies.data.response.TvShowListBean
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query(
            "api_key"
        ) apiKey: String
    ): MovieListBean

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query(
            "api_key"
        ) apiKey: String
    ): TvShowListBean
}