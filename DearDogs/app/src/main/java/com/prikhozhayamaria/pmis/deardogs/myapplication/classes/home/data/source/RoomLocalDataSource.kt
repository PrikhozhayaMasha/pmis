package com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data.source

import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.WorkResult
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.Dog
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data.LocalDataSource
import com.prikhozhayamaria.pmis.deardogs.myapplication.di.toDogEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.UUID

class RoomLocalDataSource internal constructor(
    private val dogsDao: DogsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalDataSource {
    override fun getDogsFlow(): Flow<WorkResult<List<Dog>>> {
        return dogsDao.observeDogs().map {
            WorkResult.Success(it.map { dogEntity -> dogEntity.toDog() })
        }
    }

    override fun getDogFlow(dogId: UUID): Flow<WorkResult<Dog?>> {
        return dogsDao.observeDogById(dogId).map {
            WorkResult.Success(it?.toDog())
        }
    }

    override suspend fun setDogs(dogs: List<Dog>) {
        dogsDao.setDogs(dogs.map { it.toDogEntity() })
    }

    override suspend fun addDog(dog: Dog) {
        dogsDao.insertDog(dog.toDogEntity())
    }

    override suspend fun deleteDog(id: UUID) = withContext<Unit>(ioDispatcher) {
        dogsDao.deleteDogById(id)
    }
}
