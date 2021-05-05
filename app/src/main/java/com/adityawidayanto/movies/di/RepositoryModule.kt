package com.adityawidayanto.movies.di

import com.adityawidayanto.movies.data.repository.MovieRepositoryImpl
import com.adityawidayanto.movies.data.repository.TvShowRepositoryImpl
import com.adityawidayanto.movies.domain.repository.MovieRepository
import com.adityawidayanto.movies.domain.repository.TvShowRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository

    @Binds
    fun bindTvShowRepository(repository: TvShowRepositoryImpl): TvShowRepository
}