package com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.viewModels


import java.util.UUID

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.WorkResult
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.Dog
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.DogsRepository
import com.prikhozhayamaria.pmis.deardogs.myapplication.domain.AddDogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DogsListUiState(
    val dogs: List<Dog> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

@HiltViewModel
class DogsListViewModel @Inject constructor(
    private val addDogUseCase: AddDogUseCase,
    private val dogsRepository: DogsRepository
): ViewModel() {
    private val dogs = dogsRepository.getDogsFlow()

    //How many things are we waiting for to load?
    private val numLoadingItems = MutableStateFlow(0)

    val uiState = combine(dogs, numLoadingItems) { dogs, loadingItems ->
        when (dogs) {
            is WorkResult.Error -> DogsListUiState(isError = true)
            is WorkResult.Loading -> DogsListUiState(isLoading = true)
            is WorkResult.Success -> DogsListUiState(dogs = dogs.data, isLoading = loadingItems > 0)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DogsListUiState(isLoading = true)
    )



    fun deleteDog(dogId: UUID) {
        viewModelScope.launch {
            withLoading {
                dogsRepository.deleteDog(dogId)
            }
        }
    }

    fun refreshDogsList() {
        viewModelScope.launch {
            withLoading {
                dogsRepository.refreshDogs()
            }
        }
    }

    private suspend fun withLoading(block: suspend () -> Unit) {
        try {
            addLoadingElement()
            block()
        }
        finally {
            removeLoadingElement()
        }
    }

    private fun addLoadingElement() = numLoadingItems.getAndUpdate { num -> num + 1 }
    private fun removeLoadingElement() = numLoadingItems.getAndUpdate { num -> num - 1 }
}