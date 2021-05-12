package com.adityawidayanto.movies.di

import com.adityawidayanto.core.di.CoreComponent
import com.adityawidayanto.core.di.CoreScope
import com.adityawidayanto.movies.view.ui.detail.DetailFragment
import com.adityawidayanto.movies.view.ui.favorite.movie.FavoriteMovieFragment
import com.adityawidayanto.movies.view.ui.favorite.tvshow.FavoriteTvShowFragment
import com.adityawidayanto.movies.view.ui.movielist.PopularMovieFragment
import com.adityawidayanto.movies.view.ui.tvshowlist.PopularTvShowFragment
import dagger.Component


@CoreScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [
        MovieModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface MovieComponent {
    fun inject(popularMovieFragment: PopularMovieFragment)
    fun inject(popularTvShowFragment: PopularTvShowFragment)
    fun inject(detailFragment: DetailFragment)
    fun inject(favoriteMovieFragment: FavoriteMovieFragment)
    fun inject(favoriteTvShowFragment: FavoriteTvShowFragment)

}