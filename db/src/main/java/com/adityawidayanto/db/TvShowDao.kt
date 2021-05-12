package com.adityawidayanto.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adityawidayanto.db.entity.TvShow


@Dao
abstract class TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(movies: List<TvShow>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(tvShow: TvShow)

    @Query("SELECT count(*) FROM popular_tvShows WHERE id = :id")
    abstract suspend fun checkIdTvShow(id: Int): Int

    @Query("DELETE FROM popular_tvShows WHERE id = :id")
    abstract suspend fun deleteFavorite(id: Int)

    @Query("DELETE FROM popular_tvShows")
    abstract suspend fun deleteAll()
}