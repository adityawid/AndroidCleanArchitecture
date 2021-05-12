package com.adityawidayanto.movies.data.repository

import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.core.utils.test.CoroutineTestRule
import com.adityawidayanto.core.utils.test.runBlockingTest
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.bean.responses.MovieListBean
import com.adityawidayanto.movies.data.repository.movie.MoviePagingSource
import com.adityawidayanto.movies.data.repository.movie.MovieRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var repository: MovieRepositoryImpl

    @Mock
    lateinit var remoteDataSource: MovieRemoteDataSource

    @Mock
    lateinit var moviePagingSource: MoviePagingSource

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

        private val moviesData = MovieListBean(movies)

    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = MovieRepositoryImpl(
            coroutineTestRule.testDispatcherProvider,
            remoteDataSource,
            moviePagingSource
        )
    }

    @Test
    fun `when fetch movie from api, get movie from network should be invoked`() =
        coroutineTestRule.runBlockingTest {
            val returnValue = Result.Success(moviesData)
            `when`(
                remoteDataSource.getPopularMovies(
                    coroutineTestRule.testDispatcher,
                    1, 10
                )
            ).thenReturn(returnValue)
            val response = repository.getPopularMovie(1, 10)
            assertEquals(returnValue, response)
        }

}