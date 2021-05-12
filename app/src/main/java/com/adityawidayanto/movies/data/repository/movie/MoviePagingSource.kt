package com.adityawidayanto.movies.data.repository.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adityawidayanto.core.utils.test.EspressoIdlingResource
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.api.MovieApi
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(private val movieApi: MovieApi, private val apiKey: String) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            EspressoIdlingResource.increment()

            val position = params.key ?: 1
            val response = movieApi.getPopularMovies(apiKey, position)
            val movies = response.movies
            EspressoIdlingResource.decrement()

            LoadResult.Page(
                data = movies,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}