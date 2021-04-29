package com.adityawidayanto.moviesshow.data.repository

import android.util.Log
import com.adityawidayanto.db.bean.TvShow
import com.adityawidayanto.moviesshow.domain.repository.TvShowRepository

class TvShowRepositoryImpl(
    private val tvShowRemoteDatasource: TvShowRemoteDataSource,
) : TvShowRepository {
    override suspend fun getTvShows(): List<TvShow>? = getTvShowFromApi()

    private suspend fun getTvShowFromApi(): List<TvShow> {
        lateinit var tvShowsList: List<TvShow>
        try {
            val response = tvShowRemoteDatasource.getTvShow()
            val body = response.body()
            if (body != null) {
                tvShowsList = body.tvShows
            }
        } catch (exception: Exception) {
            tvShowsList = emptyList()
            Log.e("MovieRepositoryImpl", exception.message.toString())
        }
        return tvShowsList
    }
}