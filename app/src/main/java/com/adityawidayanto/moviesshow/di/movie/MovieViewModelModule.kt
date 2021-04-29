package com.adityawidayanto.moviesshow.di.movie

import androidx.lifecycle.ViewModel
import com.adityawidayanto.moviesshow.view.ui.movies.MoviesViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class MovieViewModelModule {
    @Binds
    internal abstract fun bindMovieViewModel(viewModel: MoviesViewModel): ViewModel
}