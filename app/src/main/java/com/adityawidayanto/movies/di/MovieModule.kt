package com.adityawidayanto.movies.di

import com.adityawidayanto.core.BuildConfig
import com.adityawidayanto.core.di.CoreScope
import com.adityawidayanto.db.MovieDao
import com.adityawidayanto.db.MovieDatabase
import com.adityawidayanto.db.TvShowDao
import com.adityawidayanto.movies.data.api.MovieApi
import com.adityawidayanto.movies.data.repository.movie.MoviePagingSource
import com.adityawidayanto.movies.data.repository.movie.MovieRemoteDataSource
import com.adityawidayanto.movies.data.repository.tvshow.TvShowRemoteDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MovieModule {

    @Provides
    @CoreScope
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @CoreScope
    fun provideMovieRemoteDataSource(characterService: MovieApi) = MovieRemoteDataSource(
        characterService,
        BuildConfig.API_KEY
    )

    @Provides
    @CoreScope
    fun provideMovieDPagingDataSource(movieApi: MovieApi) = MoviePagingSource(
        movieApi,
        BuildConfig.API_KEY
    )

    @Provides
    @CoreScope
    fun provideTvShowRemoteDataSource(characterService: MovieApi) = TvShowRemoteDataSource(
        characterService,
        BuildConfig.API_KEY
    )


    @Provides
    @CoreScope
    fun provideMovieDao(db: MovieDatabase): MovieDao = db.movieDao()

    @Provides
    @CoreScope
    fun provideTvShowDao(db: MovieDatabase): TvShowDao = db.tvShowDao()

}