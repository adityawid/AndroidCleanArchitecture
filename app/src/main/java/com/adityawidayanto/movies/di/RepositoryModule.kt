package com.adityawidayanto.movies.di

import com.adityawidayanto.movies.data.remote.MovieRepositoryImpl
import com.adityawidayanto.movies.data.remote.tvshow.TvShowRepositoryImpl
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