package com.adityawidayanto.movies.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.adityawidayanto.core.utils.Result.Success
import com.adityawidayanto.db.MovieDao
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.bean.responses.MovieListBean
import com.adityawidayanto.movies.data.repository.movie.MoviePagingSource
import com.adityawidayanto.movies.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class MovieUseCaseTest {

    private var repository = mock(MovieRepository::class.java)
    private lateinit var useCase: MovieUseCase

    @Mock
    lateinit var moviePagingSource: MoviePagingSource

    @Mock
    lateinit var movieDao: MovieDao

    @Before
    fun setUp() {
        useCase = MovieUseCase(repository)
    }

    companion object {
        private val movies = listOf(
            Movie(
                1,
                "overview",
                "path",
                "release",
                "title",
                "backDrop",
                5000.1,
                7.9,
                2000
            )
        )

        private var movieList: MovieListBean = MovieListBean(movies)

    }

    @Test
    fun `should show movie list response`() {
        val returnValue = Success(movieList)
        val request = runBlocking {
            `when`(repository.getPopularMovie(1, 10)).thenReturn(
                Success(movieList)
            )
            useCase.invoke()
        }
        assertEquals(returnValue, request)
    }

    @Test
    fun `should show movie paging list`() {
        val resultValue: LiveData<PagingData<Movie>> = Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { moviePagingSource }
        ).liveData
        val request = runBlocking {
            `when`(repository.getPagingPopularMovie()).thenReturn(
                resultValue
            )
            useCase.getPagingMovie()
        }
        assertEquals(resultValue, request)
    }

    @Test
    fun `insert favorite moviedb and get data`() {
        val returnValue: Flow<PagingData<Movie>> = Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { movieDao.getAllFavMovie() }
        ).flow
        runBlocking {
            repository.addMovieFavorite(
                Movie(
                    1,
                    "overview",
                    "path",
                    "release",
                    "title",
                    "backDrop",
                    5000.1,
                    7.9,
                    2000
                )
            )
        }
        val request = runBlocking {
            `when`(
                repository.getPagingFavoriteMovie(
                )
            ).thenReturn(
                returnValue
            )
            useCase.getPagingFavoriteMovie()
        }
        assertEquals(returnValue, request)
    }


}