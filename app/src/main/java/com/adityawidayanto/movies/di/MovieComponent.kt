package com.adityawidayanto.movies.di

import com.adityawidayanto.core.di.CoreComponent
import com.adityawidayanto.core.di.CoreScope
import com.adityawidayanto.movies.view.ui.movielist.PopularMovieFragment
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

}