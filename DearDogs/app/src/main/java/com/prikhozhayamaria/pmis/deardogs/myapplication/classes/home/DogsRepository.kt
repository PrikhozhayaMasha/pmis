package com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home


import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.WorkResult
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface DogsRepository {
    fun getDogsFlow(): Flow<WorkResult<List<Dog>>>
    fun getDogFlow(dogId: UUID): Flow<WorkResult<Dog?>>
    suspend fun refreshDogs()
    suspend fun addDog(dog: Dog)
    suspend fun deleteDog(dogId: UUID)
}