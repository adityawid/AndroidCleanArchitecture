package com.adityawidayanto.movies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adityawidayanto.core.di.ViewModelFactory
import com.adityawidayanto.core.di.ViewModelKey
import com.adityawidayanto.movies.view.ui.movielist.PopularMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PopularMovieViewModel::class)
    abstract fun providesMovieListViewModel(viewModel: PopularMovieViewModel): ViewModel
}