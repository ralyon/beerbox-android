package com.ralyon.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ralyon.data.api.ApiService
import com.ralyon.data.model.Beer
import com.ralyon.data.source.BeersPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val api: ApiService
) : Repository {

    override fun getBeers(): Flow<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(BEERS_PAGE_SIZE),
            pagingSourceFactory = { BeersPagingSource(api) }
        ).flow
    }

    companion object {
        private const val BEERS_PAGE_SIZE = 25
    }
}