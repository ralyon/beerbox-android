package com.ralyon.domain.usecase

import com.ralyon.data.model.AdInfo
import com.ralyon.data.repository.Repository
import javax.inject.Inject

class GetAdInfoUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): AdInfo {
        return repository.getAdInfo()
    }
}