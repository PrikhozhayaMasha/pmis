package com.prikhozhayamaria.pmis.deardogs.myapplication.di

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.Dog
import java.util.UUID

@Entity(tableName = DogEntity.TableName)
data class DogEntity(
    val nickname: String= "",
    val breed: String= "",
    val image: String= "",
    val age: String= "",
    val color: String= "",
    val obedience: Float= 0.0f,
    @PrimaryKey val id: UUID, // java.util.UUID, сила котлина
){

    fun toDog(): Dog {
        return Dog(
            id = id,
            nickname = nickname,
            breed = breed,
            image = image,
            age = age,
            color = color,
            obedience = obedience
        )
    }

    internal companion object {
        const val TableName = "Dogs" // <- удобно потом ссылаться будет
    }
}

fun Dog.toDogEntity(): DogEntity {
    return DogEntity(
        id = id ?: UUID.randomUUID(),
        nickname = nickname,
        breed = breed,
        image = image,
        age = age,
        color = color,
        obedience = obedience
    )
}