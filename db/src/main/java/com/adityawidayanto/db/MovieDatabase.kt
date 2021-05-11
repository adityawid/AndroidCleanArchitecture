package com.adityawidayanto.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adityawidayanto.db.dao.MovieDao
import com.adityawidayanto.db.dao.TvShowDao
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.db.entity.TvShow

@Database(entities = [Movie::class, TvShow::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}