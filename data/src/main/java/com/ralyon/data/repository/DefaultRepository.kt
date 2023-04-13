package com.ralyon.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ralyon.data.api.ApiService
import com.ralyon.data.model.AdInfo
import com.ralyon.data.model.Beer
import com.ralyon.data.source.BeersPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val api: ApiService
) : Repository {

    override fun getBeers(beerName: String?, malt: String?): Flow<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(BEERS_PAGE_SIZE),
            pagingSourceFactory = {
                BeersPagingSource(
                    api = api,
                    beerName = beerName,
                    malt = malt
                )
            }
        ).flow
    }

    override suspend fun getAdInfo(): AdInfo {
        // an api call could be done here
        return AdInfo(
            title = "Weekend Offers",
            description = "Free shipping on orders over 60€"
        )
    }

    companion object {
        private const val BEERS_PAGE_SIZE = 25
    }
}