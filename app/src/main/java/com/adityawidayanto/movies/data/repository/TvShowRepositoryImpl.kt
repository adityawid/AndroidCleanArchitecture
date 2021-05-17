package com.adityawidayanto.movies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.core.utils.dispatcher.DispatcherProvider
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.data.bean.responses.TvShowListBean
import com.adityawidayanto.movies.data.repository.tvshow.TvShowLocalDataSource
import com.adityawidayanto.movies.data.repository.tvshow.TvShowRemoteDataSource
import com.adityawidayanto.movies.domain.repository.TvShowRepository
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val remoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource
) : TvShowRepository {

    override suspend fun getPopularTvShow(
        page: Int,
        pageSize: Int
    ): Result<TvShowListBean> {
        return when (val apiResult =
            remoteDataSource.getPopularTvShow(dispatcher.io, page, pageSize)) {
            is Result.Loading -> Result.Loading
            is Result.Success -> {
                Result.Success(apiResult.data)
            }
            is Result.Error -> Result.Error(apiResult.cause, apiResult.code, apiResult.errorMessage)
        }

    }

    override fun getPagingPopularTvShow(): LiveData<PagingData<TvShow>> {
        return MutableLiveData()
    }

    override fun getPagingFavoriteTvShow(): LiveData<PagingData<TvShow>> =
        tvShowLocalDataSource.getFavTvShows()

    override suspend fun addTvShowFavorite(tvShow: TvShow) {
        tvShowLocalDataSource.addFavoriteTvShow(tvShow)
    }

    override suspend fun deleteTvShowFavorite(tvShow: TvShow) {
        tvShowLocalDataSource.deleteTvShowFavorite(tvShow)
    }

    override suspend fun checkTvShowFavorite(tvShow: TvShow): Int =
        tvShowLocalDataSource.checkTvShowFavorite(tvShow)
}