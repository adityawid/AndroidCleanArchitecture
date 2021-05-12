package com.adityawidayanto.movies.data.api

import com.adityawidayanto.movies.data.bean.responses.MovieListBean
import com.adityawidayanto.movies.data.bean.responses.TvShowListBean
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query(
            "api_key"
        ) apiKey: String,
        @Query(
            "page"
        ) page: Int
    ): MovieListBean

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query(
            "api_key"
        ) apiKey: String
    ): TvShowListBean
}