package com.adityawidayanto.moviesshow.di.tvshow

import com.adityawidayanto.core.network.ServiceGenerator
import com.adityawidayanto.moviesshow.data.api.MovieApi
import com.adityawidayanto.moviesshow.data.repository.TvShowRemoteDataSource
import com.adityawidayanto.moviesshow.data.repository.TvShowRemoteDataSourceImpl
import com.adityawidayanto.moviesshow.data.repository.TvShowRepositoryImpl
import com.adityawidayanto.moviesshow.domain.repository.TvShowRepository
import com.adityawidayanto.moviesshow.domain.usecase.TvShowUseCase
import com.adityawidayanto.moviesshow.view.ui.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {
    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory(
        tvShowUseCase: TvShowUseCase,
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(
            tvShowUseCase
        )
    }

    @TvShowScope
    @Provides
    fun provideMovieApi(): MovieApi {
        val retrofit = ServiceGenerator().createService(provideHostUrl())
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @TvShowScope
    fun provideTvShowRepository(
        tvShowRemoteDatasource: TvShowRemoteDataSource,
    ): TvShowRepository {

        return TvShowRepositoryImpl(
            tvShowRemoteDatasource
        )
    }

    @TvShowScope
    @Provides
    fun provideTvShowUseCase(tvShowRepository: TvShowRepository): TvShowUseCase {
        return TvShowUseCase(tvShowRepository)
    }

    @TvShowScope
    @Provides
    fun provideTvShowRemoteDataSource(movieApi: MovieApi): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(
            movieApi, "e6508f8656792e766393d152f71fca5c"
        )
    }

    @TvShowScope
    @Provides
    fun provideHostUrl(): String = "https://api.themoviedb.org/3/"
//        getStringMetadata(App().getContext(), "HOST_URL")!!

}