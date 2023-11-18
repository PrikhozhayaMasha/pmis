package com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data


import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.Dog
import java.util.UUID

interface RemoteDataSource {
    suspend fun getDogs(): List<Dog>
    suspend fun addDog(dog: Dog): Dog
    suspend fun deleteDog(planetId: UUID)
}