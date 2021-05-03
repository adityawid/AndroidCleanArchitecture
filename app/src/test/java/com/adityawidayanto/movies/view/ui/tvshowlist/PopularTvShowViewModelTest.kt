package com.adityawidayanto.movies.view.ui.tvshowlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.adityawidayanto.core.network.HttpResult
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.core.utils.test.CoroutineTestRule
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.data.response.TvShowListBean
import com.adityawidayanto.movies.domain.usecase.TvShowUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.*

@ExperimentalCoroutinesApi
class PopularTvShowViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: TvShowUseCase

    @Mock
    lateinit var result: Observer<List<TvShow>>

    @Mock
    lateinit var error: Observer<Result.Error>

    @Captor
    lateinit var argResultCaptor: ArgumentCaptor<List<TvShow>>

    @Captor
    lateinit var argErrorCaptor: ArgumentCaptor<Result.Error>

    private lateinit var viewModel: PopularTvShowViewModel


    private val tvshow = listOf(
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

    private val data = TvShowListBean(tvshow)

    private val provider = CoroutineTestRule().testDispatcherProvider.unconfined

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(provider)

        viewModel = PopularTvShowViewModel(useCase)
        viewModel.tvShowList.observeForever(result)
        viewModel.error.observeForever(error)
    }

    @Test
    fun `should return a response of movies data`() = runBlocking {
        val returnValue = Result.Success(data)
        Mockito.`when`(useCase.invoke()).thenReturn(returnValue)
        viewModel.getPopularTvShow()
        Mockito.verify(result, Mockito.atLeastOnce()).onChanged(argResultCaptor.capture())
        assertEquals(returnValue.data.tvShows, argResultCaptor.allValues.first())
        Mockito.clearInvocations(useCase, result)
    }


    @Test
    fun `should return an error bad response`() = runBlocking {
        val returnValue = Result.Error(HttpResult.BAD_RESPONSE, 400, "Bad Response")
        Mockito.`when`(useCase.invoke()).thenReturn(returnValue)
        viewModel.getPopularTvShow()
        Mockito.verify(error, Mockito.atLeastOnce()).onChanged(argErrorCaptor.capture())
        assertEquals(returnValue, argErrorCaptor.allValues.first())
        Mockito.clearInvocations(useCase, error)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}