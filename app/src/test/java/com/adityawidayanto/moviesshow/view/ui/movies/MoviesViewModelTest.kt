package com.adityawidayanto.moviesshow.view.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.adityawidayanto.db.bean.Movie
import com.adityawidayanto.moviesshow.domain.usecase.MoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


interface DispatcherProvider {
    val io: CoroutineDispatcher
    val ui: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

@ExperimentalCoroutinesApi
class CoroutineTestRule(
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    val testDispatcherProvider = object : DispatcherProvider {
        override val io: CoroutineDispatcher = testDispatcher
        override val ui: CoroutineDispatcher = testDispatcher
        override val default: CoroutineDispatcher = testDispatcher
        override val unconfined: CoroutineDispatcher = testDispatcher
    }

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}

interface SchedulerProvider {
    fun ui(): CoroutineDispatcher
}

class TestSchedulerProvider : SchedulerProvider {
    override fun ui(): CoroutineDispatcher = Dispatchers.Unconfined
}

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var sut: MoviesViewModel

    @Mock
    private lateinit var useCase: MoviesUseCase

    @Mock
    lateinit var result: Observer<List<Movie>>

    @Mock
    lateinit var error: Observer<String>

    private val movies = listOf(
        Movie(
            1,
            "overview",
            "poster",
            "release",
            "title"
        ),
        Movie(
            2,
            "overview2",
            "poster2",
            "release2",
            "title2"
        )
    )

    private val moviesData = movies

    @Captor
    lateinit var argResultCaptor: ArgumentCaptor<List<Movie>>

    private val schedulerProvider = TestSchedulerProvider()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(schedulerProvider.ui())

        sut = MoviesViewModel(useCase)
        sut.movieList.observeForever(result)
    }


    @Test
    fun `when given non empty data, should emit success state to observer`() = runBlockingTest {
        //Given
//        lifecycleOwner.onResume()
//
//        val channels = arrayListOf(NewsChannel("cnn-indonesia", "id", "CNN Indonesia", "cnn-indonesia.com", "CI"))
//
//        `when`(useCase.invoke()).thenReturn(channels)
//        `when`(mapper.map(channels)).thenReturn(channels)
//
//        //When
//
//
//        //Then
//        verify(observer).onChanged(NewsChannelViewModel.NewsChannelState.Success(channels))
        val returnValue = moviesData

        `when`(useCase.execute()).thenReturn(returnValue)
        sut.getMovies()
        verify(result, atLeastOnce()).onChanged(argResultCaptor.capture())
        clearInvocations(useCase, result)
    }
}