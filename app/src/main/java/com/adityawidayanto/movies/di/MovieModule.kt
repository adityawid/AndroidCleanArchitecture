package com.adityawidayanto.movies.di

import com.adityawidayanto.core.BuildConfig
import com.adityawidayanto.core.di.CoreScope
import com.adityawidayanto.movies.data.MovieApi
import com.adityawidayanto.movies.data.remote.MovieRemoteDataSource
import com.adityawidayanto.movies.data.remote.tvshow.TvShowRemoteDataSource
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
    fun provideTvShowRemoteDataSource(characterService: MovieApi) = TvShowRemoteDataSource(
        characterService,
        BuildConfig.API_KEY
    )
}