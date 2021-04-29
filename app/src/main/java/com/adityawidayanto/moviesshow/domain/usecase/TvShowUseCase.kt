package com.adityawidayanto.moviesshow.domain.usecase

import com.adityawidayanto.db.bean.TvShow
import com.adityawidayanto.moviesshow.domain.repository.TvShowRepository

class TvShowUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute(): List<TvShow>? = tvShowRepository.getTvShows()
}