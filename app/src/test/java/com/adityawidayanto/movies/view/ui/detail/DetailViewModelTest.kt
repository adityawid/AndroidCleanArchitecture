package com.adityawidayanto.movies.view.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.core.utils.test.CoroutineTestRule
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.bean.DetailBean
import com.adityawidayanto.movies.domain.usecase.MovieUseCase
import com.adityawidayanto.movies.domain.usecase.TvShowUseCase
import com.adityawidayanto.movies.view.ui.favorite.movie.MovieDaoFake
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.*
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var tvShowUseCase: TvShowUseCase

    private lateinit var viewModel: DetailViewModel

    @ExperimentalCoroutinesApi
    private val provider = CoroutineTestRule().testDispatcherProvider.unconfined

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var detailResult: Observer<DetailBean>

    @Mock
    lateinit var error: Observer<Result.Error>

    @Captor
    lateinit var argDetailResultCaptor: ArgumentCaptor<DetailBean>

    val movies = listOf(
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
        ),
        Movie(
            2,
            "overview 2",
            "path 2",
            "release 2",
            "title 2",
            "backDrop 2",
            5000.1,
            7.9,
            2000
        ),
        Movie(
            3,
            "overview 3",
            "path 3",
            "release 3",
            "title",
            "backDrop",
            5000.1,
            7.9,
            2000
        )
    )
    private val detailBean =
        DetailBean(
            1,
            "title",
            "path",
            "overview",
            "release",
            "backDrop",
            5000.1,
            7.9,
            2000
        )


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(provider)

        viewModel = DetailViewModel(movieUseCase, tvShowUseCase)
        viewModel.detail.observeForever(detailResult)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should return detail data`() = runBlocking {
        val returnValue = Result.Success(detailBean)
        viewModel.initDetail(detailBean)

        Mockito.verify(detailResult, Mockito.atLeastOnce())
            .onChanged(argDetailResultCaptor.capture())
        assertEquals(returnValue.data, argDetailResultCaptor.allValues.first())
        Mockito.clearInvocations(movieUseCase, detailResult)
    }

    @Test
    fun `should return total data when success checkMovieID`() = runBlockingTest(provider) {

        val returnValue = 1

        `when`(movieUseCase.checkMovieFavorite(movies[0])).thenReturn(returnValue)

        val checkMovieID = viewModel.checkIdMovie(movies[0])
        assertEquals(returnValue, checkMovieID)
    }

    @Test
    fun `should return 0 when checkMovieID not exist`() = runBlockingTest(provider) {
        val returnValue = 0

        `when`(movieUseCase.checkMovieFavorite(movies[0])).thenReturn(returnValue)

        val checkMovieID = viewModel.checkIdMovie(movies[0])
        assertEquals(returnValue, checkMovieID)
    }

    @Test
    fun `should return 1 when Add and checkID Movie`() = runBlockingTest(provider) {
        val fakeDao = MovieDaoFake(movies)
        `when`(movieUseCase.addMovieFavorite(movies[0])).then {
            runBlocking {
                fakeDao.insert(
                    Movie(
                        4,
                        "overview 4",
                        "path4",
                        "release4",
                        "title4",
                        "backDrop",
                        5000.1,
                        7.9,
                        2000
                    )
                )
            }
        }
        viewModel.addMovieFavorite(movies[0])
        val count = fakeDao.checkIdMovie(4)
        assertThat(count).isEqualTo(1)
    }

    @Test
    fun `should return 0 when remove favorite and checkID Movie`() = runBlockingTest(provider) {
        val fakeDao = MovieDaoFake(movies)
        fakeDao.insert(
            Movie(
                5,
                "overview 4",
                "path4",
                "release4",
                "title4",
                "backDrop",
                5000.1,
                7.9,
                2000
            )
        )
        `when`(movieUseCase.removeMovieFavorite(movies[0])).then {
            runBlocking {
                fakeDao.deleteFavorite(
                    4
                )
            }
        }
        viewModel.removeMovieFavorite(movies[0])
        val count = fakeDao.checkIdMovie(4)
        assertThat(count).isEqualTo(0)
    }
}