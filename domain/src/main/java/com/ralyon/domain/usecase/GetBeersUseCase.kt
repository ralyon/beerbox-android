package com.ralyon.domain.usecase

import androidx.paging.PagingData
import com.ralyon.data.model.Beer
import com.ralyon.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeersUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(beerName: String?, malt: String?): Flow<PagingData<Beer>> {
        val asd = beerName
            ?.replace(' ', '_')
            ?.takeIf { it.isNotBlank() }
        return repository.getBeers(asd, malt)
    }

}