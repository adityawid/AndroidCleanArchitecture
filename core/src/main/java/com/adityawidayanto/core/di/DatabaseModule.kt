package com.adityawidayanto.core.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.adityawidayanto.core.utils.Constants
import com.adityawidayanto.db.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): MovieDatabase =
        databaseBuilder(context, MovieDatabase::class.java, Constants.DATABASE_NAME)
            .build()
}