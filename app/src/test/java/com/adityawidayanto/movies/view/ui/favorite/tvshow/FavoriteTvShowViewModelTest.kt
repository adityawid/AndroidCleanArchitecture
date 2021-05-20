package com.adityawidayanto.movies.view.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.adityawidayanto.core.utils.test.CoroutineTestRule
import com.adityawidayanto.db.TvShowDao
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.domain.repository.TvShowRepository
import com.adityawidayanto.movies.domain.usecase.TvShowUseCase
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
class FavoriteTvShowViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowUseCase: TvShowUseCase

    private lateinit var viewModel: FavoriteTvShowViewModel

    @Mock
    lateinit var tvShowDao: TvShowDao

    @Mock
    lateinit var repository: TvShowRepository

    @ExperimentalCoroutinesApi
    private val provider = CoroutineTestRule().testDispatcherProvider.unconfined


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(provider)

        viewModel = FavoriteTvShowViewModel(tvShowUseCase)
    }

    @Test
    fun `should return a response of favorite tv show data`() = runBlockingTest {
        runBlockingTest {
            repository.addTvShowFavorite(
                TvShow(
                    "firstAirDate",
                    1,
                    "name",
                    "overview",
                    "path",
                    "backDrop",
                    2000.1,
                    9.0,
                    50010
                )
            )
        }

        val returnValue = Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { tvShowDao.getAllFavTvShow() }
        ).liveData

        Mockito.`when`(
            tvShowUseCase.getPagingFavoriteTvShow()
        ).thenReturn(
            returnValue
        )


        val req = tvShowUseCase.getPagingFavoriteTvShow()

        advanceUntilIdle()
        Assert.assertEquals(returnValue, req)
        Mockito.clearInvocations(tvShowUseCase)
    }

}