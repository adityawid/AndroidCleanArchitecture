package com.adityawidayanto.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adityawidayanto.db.entity.Movie

@Dao
abstract class MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(movies: Movie)

    @Query("SELECT * FROM popular_movies")
    abstract fun findAll(): DataSource.Factory<Int, Movie>

    @Query("DELETE FROM popular_movies")
    abstract suspend fun deleteAll()
}