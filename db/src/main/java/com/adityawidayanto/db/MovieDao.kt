package com.adityawidayanto.db

import androidx.lifecycle.LiveData
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

    @Query("SELECT count(*) FROM popular_movies WHERE id = :id")
    abstract suspend fun checkIdMovie(id: Int): Int

    @Query("SELECT * FROM popular_movies")
    abstract fun findAll(): LiveData<Movie>

    @Query("DELETE FROM popular_movies WHERE id = :id")
    abstract suspend fun deleteFavorite(id: Int)

    @Query("DELETE FROM popular_movies")
    abstract suspend fun deleteAll()
}