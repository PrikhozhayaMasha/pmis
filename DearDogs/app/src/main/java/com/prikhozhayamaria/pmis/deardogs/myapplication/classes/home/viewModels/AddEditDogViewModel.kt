package com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prikhozhayamaria.pmis.deardogs.myapplication.R
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.WorkResult
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.Dog
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.DogsRepository
import com.prikhozhayamaria.pmis.deardogs.myapplication.domain.AddDogUseCase
import com.prikhozhayamaria.pmis.deardogs.myapplication.ui.navigation.DogsDestinationsArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class AddEditDogUiState(
//    val memory: String = "",
//   // val planetDistanceLy: Float = 1.0F,
//    val memoryAdded: Date=Date(),
//    val isLoading: Boolean = false,
//    val isMemorySaved: Boolean = false,
//    val isMemorySaving: Boolean = false,
//    val memorySavingError: Int? = null

    val nickname: String= "",
    val breed: String= "",
    val image: String= "",
    val age: String= "",
    val color: String= "",
    val obedience: Float= 0.0f,
    val isLoading: Boolean = false,
    val isDogSaved: Boolean = false,
    val isDogSaving: Boolean = false,
    val dogSavingError: Int? = null
)

@HiltViewModel
class AddEditDogViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val addDogUseCase: AddDogUseCase,
    private val dogsRepository: DogsRepository
): ViewModel() {
    private var dogId: String? = savedStateHandle[DogsDestinationsArgs.DOG_ID_ARG]

    private val _uiState = MutableStateFlow(AddEditDogUiState())
    val uiState: StateFlow<AddEditDogUiState> = _uiState.asStateFlow()

    init {
        if (dogId != null) {
            loadDog(UUID.fromString(dogId))
        }
    }

    fun deleteDog() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isDogSaving = true) }
                System.out.println(dogId.toString())
                if(dogId!=null) {
                    dogsRepository.deleteDog(UUID.fromString(dogId))
                }
                _uiState.update { it.copy(isDogSaved = true) }
            }
            catch (e: Exception) {
                System.out.println(e)
                _uiState.update { it.copy(dogSavingError = R.string.error_saving_dog) }
            }
            finally {
                _uiState.update { it.copy(isDogSaving = false) }
            }
            // withLoading {
            // }
        }
    }

    fun saveDog() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isDogSaving = true) }
                System.out.println(dogId.toString() + _uiState.value.nickname)
                if(dogId!=null) {
                    addDogUseCase(
                        Dog(
                            id = UUID.fromString(dogId),
                            nickname = _uiState.value.nickname,
                            breed = _uiState.value.breed,
                            age = _uiState.value.age,
                            color = _uiState.value.color,
                            image = _uiState.value.image,
                            obedience = _uiState.value.obedience
                        )
                    )
                }else{
                    addDogUseCase(
                        Dog(
                            id = null,
                            nickname = _uiState.value.nickname,
                            breed = _uiState.value.breed,
                            age = _uiState.value.age,
                            color = _uiState.value.color,
                            image = _uiState.value.image,
                            obedience = _uiState.value.obedience
                        )
                    )
                }
                _uiState.update { it.copy(isDogSaved = true) }
            }
            catch (e: Exception) {
                System.out.println(e)
                _uiState.update { it.copy(dogSavingError = R.string.error_saving_dog) }
            }
            finally {
                _uiState.update { it.copy(isDogSaving = false) }
            }
        }
    }

    private fun loadDog(dogId: UUID) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = dogsRepository.getDogFlow(dogId).first()
            if (result !is WorkResult.Success || result.data == null) {
                _uiState.update { it.copy(isLoading = false) }
            }
            else {
                val dog = result.data
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        nickname = dog.nickname,
                        breed = dog.breed,
                        age = dog.age,
                        color = dog.color,
                        image = dog.image,
                        obedience = dog.obedience
                    )
                }
            }
        }
    }

    fun setDogNickname(name: String) {
        _uiState.update { it.copy(nickname = name) }
    }
    fun setDogBreed(breed: String) {
        _uiState.update { it.copy(breed = breed) }
    }
    fun setDogAge(age: String) {
        _uiState.update { it.copy(age = age) }
    }
    fun setDogColor(color: String) {
        _uiState.update { it.copy(color = color) }
    }
    fun setDogImage(image: String) {
        _uiState.update { it.copy(image = image) }
    }
    fun setDogObedience(obedience: Float) {
        _uiState.update { it.copy(obedience = obedience) }
    }

}