package com.ralyon.data.repository

import androidx.paging.PagingData
import com.ralyon.data.model.AdInfo
import com.ralyon.data.model.Beer
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getBeers(): Flow<PagingData<Beer>>

    suspend fun getAdInfo(): AdInfo
}