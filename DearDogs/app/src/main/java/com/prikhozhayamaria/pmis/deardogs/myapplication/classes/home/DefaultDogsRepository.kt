package com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home


import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data.LocalDataSource
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data.RemoteDataSource
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.WorkResult

class DefaultDogsRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val ioCoroutineDispatcher: CoroutineDispatcher
): DogsRepository {
    override fun getDogsFlow(): Flow<WorkResult<List<Dog>>> {
        return localDataSource.getDogsFlow()
    }

    override fun getDogFlow(dogId: UUID): Flow<WorkResult<Dog?>> {
        return localDataSource.getDogFlow(dogId)
    }

    override suspend fun refreshDogs() {
        val dogs = remoteDataSource.getDogs()
        localDataSource.setDogs(dogs)
    }
    override suspend fun addDog(dog: Dog) {
        val dogWithId= remoteDataSource.addDog(dog)
        localDataSource.addDog(dogWithId)
    }

    override suspend fun deleteDog(dogId: UUID) {
        remoteDataSource.deleteDog(dogId)
        localDataSource.deleteDog(dogId)
    }
}