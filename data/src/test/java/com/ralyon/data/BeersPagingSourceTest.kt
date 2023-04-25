package com.ralyon.data

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams.*
import androidx.paging.PagingSource.LoadResult.Page
import com.ralyon.data.api.FakeApiService
import com.ralyon.data.source.BeersPagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BeersPagingSourceTest {

    private val fakeApi = FakeApiService()

    @Test
    fun firstPageSuccess() = runTest {
        val pagingSource = BeersPagingSource(fakeApi, null, null)
        val expected = Page(
            data = fakeApi.fakeBeers.take(2),
            prevKey = null,
            nextKey = 2
        )
        val actual = pagingSource.load(
            Refresh(
                key = null,
                loadSize = FakeApiService.FAKE_PAGE_SIZE,
                placeholdersEnabled = false
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun appendPageSuccess() = runTest {
        val pagingSource = BeersPagingSource(fakeApi, null, null)
        val expected = Page(
            data = fakeApi.fakeBeers.subList(2, 4),
            prevKey = 1,
            nextKey = 3
        )
        val actual = pagingSource.load(
            Append(
                key = 2,
                loadSize = FakeApiService.FAKE_PAGE_SIZE,
                placeholdersEnabled = false
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun prependPageError() = runTest {
        val pagingSource = BeersPagingSource(fakeApi, null, null)
        val actual = pagingSource.load(
            Prepend(
                key = 0,
                loadSize = FakeApiService.FAKE_PAGE_SIZE,
                placeholdersEnabled = false
            )
        )
        assertTrue(actual is PagingSource.LoadResult.Error)
    }
}