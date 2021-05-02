package com.adityawidayanto.movies.domain.usecase

import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.movies.data.response.TvShowListBean
import com.adityawidayanto.movies.domain.repository.TvShowRepository
import javax.inject.Inject

class TvShowUseCase @Inject constructor(private val repository: TvShowRepository) {
    suspend operator fun invoke(): Result<TvShowListBean> {
        return repository.getPopularTvShow(1, 10)
    }
}