package com.ralyon.data.repository

import com.ralyon.data.api.ApiService
import com.ralyon.data.model.Beer
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val api: ApiService
) : Repository {

    override suspend fun getBeers(page: Int?): List<Beer> {
        return api.getBeers(page)
    }

}