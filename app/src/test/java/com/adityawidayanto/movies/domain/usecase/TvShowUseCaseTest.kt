package com.adityawidayanto.movies.domain.usecase

import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.data.bean.responses.TvShowListBean
import com.adityawidayanto.movies.domain.repository.TvShowRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class TvShowUseCaseTest {

    private var repository = Mockito.mock(TvShowRepository::class.java)
    private lateinit var useCase: TvShowUseCase

    @Before
    fun setUp() {
        useCase = TvShowUseCase(repository)
    }

    companion object {
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

        private var tvShowList: TvShowListBean = TvShowListBean(tvshow)

    }

    @Test
    fun `should show movie list response`() {
        val returnValue = Result.Success(tvShowList)
        val request = runBlocking {
            Mockito.`when`(repository.getPopularTvShow(1, 10)).thenReturn(
                Result.Success(tvShowList)
            )
            useCase.invoke()
        }
        assertEquals(returnValue, request)
    }
}