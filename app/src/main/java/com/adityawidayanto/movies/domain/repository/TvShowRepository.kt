package com.adityawidayanto.movies.domain.repository

import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.movies.data.response.TvShowListBean

interface TvShowRepository {
    suspend fun getPopularTvShow(page: Int, pageSize: Int): Result<TvShowListBean>
}