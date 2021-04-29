package com.adityawidayanto.moviesshow.di

import android.content.Context
import com.adityawidayanto.moviesshow.app.App
import com.adityawidayanto.moviesshow.di.movie.MovieComponent
import com.adityawidayanto.moviesshow.di.tvshow.TvShowComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [MovieComponent::class, TvShowComponent::class])
class AppModule {
    @Singleton
    @Provides
    fun provideContext(app: App): Context = app.applicationContext
}