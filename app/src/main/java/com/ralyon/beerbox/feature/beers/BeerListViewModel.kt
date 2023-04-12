package com.ralyon.beerbox.feature.beers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ralyon.data.model.Beer
import com.ralyon.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getBeers(): Flow<PagingData<Beer>> = repository.getBeers().cachedIn(viewModelScope)

}