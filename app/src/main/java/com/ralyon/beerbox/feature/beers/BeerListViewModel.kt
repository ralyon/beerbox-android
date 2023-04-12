package com.ralyon.beerbox.feature.beers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.ralyon.data.model.AdInfo
import com.ralyon.data.model.Beer
import com.ralyon.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BeerListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAdInfo()
    }

    fun getBeers(search: String = ""): Flow<PagingData<Beer>> = repository.getBeers()
        .map { beers ->
            beers.filter { it.name.startsWith(search, true) }
        }
        .cachedIn(viewModelScope)

    private fun getAdInfo() {
        viewModelScope.launch {
            val info = try {
                repository.getAdInfo()
            } catch (e: Exception) {
                null
            }

            _uiState.update { it.copy(adInfo = info) }
        }
    }
}

data class BeerListUiState(
    val adInfo: AdInfo? = null
)