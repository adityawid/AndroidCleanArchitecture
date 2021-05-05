package com.adityawidayanto.movies.domain.usecase

import com.adityawidayanto.core.utils.Result.Success
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.bean.responses.MovieListBean
import com.adityawidayanto.movies.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class MovieUseCaseTest {

    private var repository = mock(MovieRepository::class.java)
    private lateinit var useCase: MovieUseCase

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
}