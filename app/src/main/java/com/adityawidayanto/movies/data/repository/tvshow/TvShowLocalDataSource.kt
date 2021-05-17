package com.adityawidayanto.movies.data.repository.tvshow

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.adityawidayanto.db.TvShowDao
import com.adityawidayanto.db.entity.TvShow

class TvShowLocalDataSource(
    private val tvShowDao: TvShowDao
) {
    fun getFavTvShows() = Pager(
        config = PagingConfig(
            10
        )
    )
    { tvShowDao.getAllFavTvShow() }
        .liveData

    suspend fun addFavoriteTvShow(tvShow: TvShow) {
        tvShowDao.insert(tvShow)
    }

    suspend fun deleteTvShowFavorite(tvShow: TvShow) {
        tvShowDao.deleteFavorite(tvShow.id)
    }

    suspend fun checkTvShowFavorite(tvShow: TvShow): Int = tvShowDao.checkIdTvShow(tvShow.id)

}