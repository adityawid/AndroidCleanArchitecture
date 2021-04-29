package com.adityawidayanto.moviesshow.di

import android.content.Context
import com.adityawidayanto.moviesshow.app.App
import com.adityawidayanto.moviesshow.di.movie.MovieComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [MovieComponent::class])
class AppModule {
    @Singleton
    @Provides
    fun provideContext(app: App): Context = app.applicationContext
}