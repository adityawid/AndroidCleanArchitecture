package com.adityawidayanto.moviesshow.di.tvshow

import com.adityawidayanto.moviesshow.view.ui.tvshow.TvShowFragment
import dagger.Subcomponent

@TvShowScope
@Subcomponent(modules = [TvShowModule::class])
interface TvShowComponent {
    fun inject(tvShowFragment: TvShowFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): TvShowComponent
    }
}