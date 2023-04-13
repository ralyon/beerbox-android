package com.ralyon.beerbox.feature.beers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
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

    fun setSearchedName(name: String) {
        _uiState.update { it.copy(searchedName = name) }
    }

    fun setSelectedMalt(name: String?) {
        _uiState.update {
            it.copy(selectedMalt = name)
        }
    }

    fun getBeers(
        beerName: String? = null,
        malt: String? = null
    ): Flow<PagingData<Beer>> = repository.getBeers(beerName, malt).cachedIn(viewModelScope)

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
    val adInfo: AdInfo? = null,
    val searchedName: String? = null,
    val selectedMalt: String? = null
)