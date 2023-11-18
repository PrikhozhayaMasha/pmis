package com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data

import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.WorkResult
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.Dog
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LocalDataSource {
    fun getDogsFlow(): Flow<WorkResult<List<Dog>>>
    fun getDogFlow(dogId: UUID): Flow<WorkResult<Dog?>>
    suspend fun setDogs(dogs: List<Dog>)
    suspend fun addDog(dog: Dog)
    suspend fun deleteDog(dogId: UUID)

}