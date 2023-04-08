package com.ralyon.domain.usecase

import com.ralyon.data.model.Beer
import com.ralyon.data.repository.Repository
import javax.inject.Inject

class GetBeersUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Beer> {
        return repository.getBeers(1)
    }
}