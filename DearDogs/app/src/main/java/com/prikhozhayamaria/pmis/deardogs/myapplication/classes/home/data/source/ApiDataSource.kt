package com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data.source

import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.Dog
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data.RemoteDataSource
import java.util.UUID
import javax.inject.Inject

class ApiRemoteDataSource @Inject constructor(): RemoteDataSource {
    private val dogsCache = ArrayList<Dog>()
    private var lastDelay = 0L

    override suspend fun getDogs(): List<Dog> {
        return dogsCache
    }

    override suspend fun addDog(dog: Dog): Dog {
        val dogToAdd = if (dog.id.toString() == "11") dog.copy(id = UUID.randomUUID()) else dog
        dogsCache.add(dogToAdd)
        return dogToAdd
    }

    override suspend fun deleteDog(id: UUID) {
        dogsCache.removeIf { it.id == id }
    }
}