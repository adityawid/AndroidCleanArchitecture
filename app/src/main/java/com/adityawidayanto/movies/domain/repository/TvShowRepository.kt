package com.adityawidayanto.movies.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.data.bean.responses.TvShowListBean

interface TvShowRepository {
    suspend fun getPopularTvShow(page: Int, pageSize: Int): Result<TvShowListBean>
    fun getPagingPopularTvShow(): LiveData<PagingData<TvShow>>
    suspend fun addTvShowFavorite(tvShow: TvShow)
    suspend fun deleteTvShowFavorite(tvShow: TvShow)
    suspend fun checkTvShowFavorite(tvShow: TvShow): Int
}