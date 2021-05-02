package com.adityawidayanto.movies.data.remote.tvshow

import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.core.utils.dispatcher.DispatcherProvider
import com.adityawidayanto.movies.data.response.TvShowListBean
import com.adityawidayanto.movies.domain.repository.TvShowRepository
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val remoteDataSource: TvShowRemoteDataSource
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
}