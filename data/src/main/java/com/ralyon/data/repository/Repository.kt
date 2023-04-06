package com.ralyon.data.repository

import com.ralyon.data.model.Beer

interface Repository {

    suspend fun getBeers(
        page: Int?,
        brewedAfter: String?,
        brewedBefore: String?
    ): List<Beer>

}