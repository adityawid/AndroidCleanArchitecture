package com.adityawidayanto.movies.view.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.adityawidayanto.core.utils.test.CoroutineTestRule
import com.adityawidayanto.db.MovieDao
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.domain.repository.MovieRepository
import com.adityawidayanto.movies.domain.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class FavoriteMovieViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    private lateinit var viewModel: FavoriteMovieViewModel

    @Mock
    lateinit var movieDao: MovieDao

    @Mock
    lateinit var repository: MovieRepository

    @ExperimentalCoroutinesApi
    private val provider = CoroutineTestRule().testDispatcherProvider.unconfined

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(provider)

        viewModel = FavoriteMovieViewModel(movieUseCase)
    }

    @Test
    fun `should return a response of favorite movies data`() = runBlockingTest {
        runBlockingTest {
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

        val returnValue = Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { movieDao.getAllFavMovie() }
        ).liveData

        Mockito.`when`(
            movieUseCase.getPagingFavoriteMovie(
            )
        ).thenReturn(
            returnValue
        )


        val req = movieUseCase.getPagingFavoriteMovie()

        advanceUntilIdle()
        Assert.assertEquals(returnValue, req)
        Mockito.clearInvocations(movieUseCase)
    }

}