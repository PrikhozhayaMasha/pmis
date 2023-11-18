package com.prikhozhayamaria.pmis.deardogs.myapplication.classes.api

import kotlinx.serialization.Serializable

@Serializable
data class DogAPI (
    val name: String,
    val image_link: String
)
