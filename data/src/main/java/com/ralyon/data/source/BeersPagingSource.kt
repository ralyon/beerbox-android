package com.ralyon.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ralyon.data.api.ApiService
import com.ralyon.data.model.Beer
import javax.inject.Inject

class BeersPagingSource @Inject constructor(
    private val api: ApiService,
    private val beerName: String?,
    private val malt: String?
) : PagingSource<Int, Beer>() {

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        return try {
            val page = params.key ?: 1
            val response = api.getBeers(
                page = page,
                beerName = beerName,
                malt = malt
            )

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}