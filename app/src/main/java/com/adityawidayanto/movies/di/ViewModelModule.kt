package com.adityawidayanto.movies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adityawidayanto.core.di.ViewModelFactory
import com.adityawidayanto.core.di.ViewModelKey
import com.adityawidayanto.movies.view.ui.detail.DetailViewModel
import com.adityawidayanto.movies.view.ui.favorite.movie.FavoriteMovieViewModel
import com.adityawidayanto.movies.view.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.adityawidayanto.movies.view.ui.movielist.PopularMovieViewModel
import com.adityawidayanto.movies.view.ui.tvshowlist.PopularTvShowViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(PopularTvShowViewModel::class)
    abstract fun providesTvShowListViewModel(viewModel: PopularTvShowViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun providesDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteMovieViewModel::class)
    abstract fun providesFavoriteMovieViewModel(viewModel: FavoriteMovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteTvShowViewModel::class)
    abstract fun providesFavoriteTvShowViewModel(viewModel: FavoriteTvShowViewModel): ViewModel
}