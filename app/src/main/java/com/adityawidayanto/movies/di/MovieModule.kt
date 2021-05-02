package com.adityawidayanto.movies.di

import com.adityawidayanto.core.BuildConfig
import com.adityawidayanto.core.di.CoreScope
import com.adityawidayanto.movies.data.MovieApi
import com.adityawidayanto.movies.data.remote.MovieRemoteDataSource
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
    fun provideRemoteDataSource(characterService: MovieApi) = MovieRemoteDataSource(
        characterService,
        BuildConfig.API_KEY
    )
}