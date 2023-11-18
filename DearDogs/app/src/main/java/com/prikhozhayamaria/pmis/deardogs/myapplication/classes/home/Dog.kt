package com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home

import java.util.UUID

data class Dog(

    val nickname: String,
    val breed: String,
    val image: String,
    val age: String,
    val color: String,
    val obedience: Float,
    val id: UUID? = null
)