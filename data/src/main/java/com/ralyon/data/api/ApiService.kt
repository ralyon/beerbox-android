package com.ralyon.data.api

import com.ralyon.data.model.Beer
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int?,
        @Query("brewed_after") brewedAfter: String?,
        @Query("brewed_before") brewedBefore: String?,
    ): List<Beer>

}