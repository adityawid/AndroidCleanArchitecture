package com.adityawidayanto.movies.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.data.bean.responses.TvShowListBean
import com.adityawidayanto.movies.domain.repository.TvShowRepository
import javax.inject.Inject

class TvShowUseCase @Inject constructor(private val repository: TvShowRepository) {
    suspend operator fun invoke(): Result<TvShowListBean> {
        return repository.getPopularTvShow(1, 10)
    }

    fun getPagingTvShow(): LiveData<PagingData<TvShow>> {
        return repository.getPagingPopularTvShow()
    }

    fun getPagingFavoriteTvShow(): LiveData<PagingData<TvShow>> {
        return repository.getPagingFavoriteTvShow()
    }

    suspend fun addTvShowFavorite(tvShow: TvShow) {
        repository.addTvShowFavorite(tvShow)
    }

    suspend fun removeTvShowFavorite(tvShow: TvShow) {
        repository.deleteTvShowFavorite(tvShow)
    }

    suspend fun checkTvShowFavorite(tvShow: TvShow): Int =
        repository.checkTvShowFavorite(tvShow)

}