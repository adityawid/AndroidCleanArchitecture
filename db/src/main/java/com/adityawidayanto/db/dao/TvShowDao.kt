package com.adityawidayanto.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adityawidayanto.db.entity.TvShow

@Dao
abstract class TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(tvShows: List<TvShow>)

    @Query("SELECT * FROM popular_tvShows")
    abstract fun findAll(): DataSource.Factory<Int, TvShow>

    @Query("DELETE FROM popular_tvShows")
    abstract suspend fun deleteAll()
}