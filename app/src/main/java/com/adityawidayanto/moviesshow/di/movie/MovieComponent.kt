package com.adityawidayanto.moviesshow.di.movie

import com.adityawidayanto.moviesshow.view.ui.movies.MoviesFragment
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class, MovieViewModelModule::class])
interface MovieComponent {
    fun inject(moviesFragment: MoviesFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieComponent
    }
}