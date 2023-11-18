package com.prikhozhayamaria.pmis.deardogs.myapplication.classes.api.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.api.DogAPI
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.api.DogsAPIRepository
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.Dog
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.DogsRepository
import com.prikhozhayamaria.pmis.deardogs.myapplication.domain.AddDogUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class DogAPIViewModel @Inject constructor(
    private val repository: DogsAPIRepository
) :  ViewModel() {

    private val _dogsapi = MutableStateFlow<List<DogAPI>>(emptyList())
    val dogsapi: StateFlow<List<DogAPI>> = _dogsapi

    init {
        fetchDogsAPI()
    }

    private fun fetchDogsAPI() {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedDogsAPI = repository.getDogsAPI()
            _dogsapi.emit(fetchedDogsAPI)
        }
    }
}