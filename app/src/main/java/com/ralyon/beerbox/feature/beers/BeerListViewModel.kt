package com.ralyon.beerbox.feature.beers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralyon.data.model.Beer
import com.ralyon.domain.usecase.GetBeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BeerListUiState())
    val uiState = _uiState.asStateFlow()

    fun gerBeers() {
        viewModelScope.launch {
            val beers = try {
                getBeersUseCase()
            } catch (e: Exception) {
                emptyList()
            }
            _uiState.update { it.copy(beers = beers) }
        }
    }
}

data class BeerListUiState(
    val beers: List<Beer> = emptyList()
)