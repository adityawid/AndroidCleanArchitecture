package com.adityawidayanto.moviesshow.di.movie

import com.adityawidayanto.core.network.ServiceGenerator
import com.adityawidayanto.moviesshow.data.api.MovieApi
import com.adityawidayanto.moviesshow.data.repository.MovieRemoteDataSourceImpl
import com.adityawidayanto.moviesshow.data.repository.MovieRemoteDatasource
import com.adityawidayanto.moviesshow.data.repository.MovieRepositoryImpl
import com.adityawidayanto.moviesshow.domain.repository.MovieRepository
import com.adityawidayanto.moviesshow.domain.usecase.MoviesUseCase
import com.adityawidayanto.moviesshow.view.ui.movies.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {
    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: MoviesUseCase,
    ): MovieViewModelFactory {
        return MovieViewModelFactory(
            getMoviesUseCase
        )
    }

    @MovieScope
    @Provides
    fun provideMovieApi(): MovieApi {
        val retrofit = ServiceGenerator().createService(provideHostUrl())
        return retrofit.create(MovieApi::class.java)
    }

//    @MovieScope
//    @Provides
//    fun provideUseCase(repository: MovieRepository): MoviesUseCase {
//        return MoviesUseCase(repository)
//    }

    @Provides
    @MovieScope
    fun provideMovieRepository(
        movieRemoteDatasource: MovieRemoteDatasource,
    ): MovieRepository {

        return MovieRepositoryImpl(
            movieRemoteDatasource
        )
    }


    @MovieScope
    @Provides
    fun provideMovieUseCase(movieRepository: MovieRepository): MoviesUseCase {
        return MoviesUseCase(movieRepository)
    }

    @MovieScope
    @Provides
    fun provideMovieRemoteDataSource(movieApi: MovieApi): MovieRemoteDatasource {
        return MovieRemoteDataSourceImpl(
            movieApi, "e6508f8656792e766393d152f71fca5c"
        )
    }

    @MovieScope
    @Provides
    fun provideHostUrl(): String = "https://api.themoviedb.org/3/"
//        getStringMetadata(App().getContext(), "HOST_URL")!!

}