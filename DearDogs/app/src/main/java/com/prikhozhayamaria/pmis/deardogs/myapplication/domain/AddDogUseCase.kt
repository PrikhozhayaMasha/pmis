package com.prikhozhayamaria.pmis.deardogs.myapplication.domain

import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.Dog
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.DogsRepository
import javax.inject.Inject

class AddDogUseCase  @Inject constructor(private val dogsRepository: DogsRepository){
    suspend operator fun invoke(dog: Dog) {
        if (dog.nickname.isEmpty()) {
            throw Exception("Please specify a dog")
        }
        if (dog.breed.isEmpty()) {
            throw Exception("Please specify a breed")
        }
        if (dog.age.isEmpty()) {
            throw Exception("Please specify a age")
        }
        if (dog.color.isEmpty()) {
            throw Exception("Please specify a color")
        }
        /*if (memory.distanceLy < 0) {
            throw Exception("Please enter a positive distance")
        }
        if (memory.discovered.after(Date())) {
            throw Exception("Please enter a discovery date in the past")
        }*/
        dogsRepository.addDog(dog)
    }
}