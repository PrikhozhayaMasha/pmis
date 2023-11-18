package com.prikhozhayamaria.pmis.deardogs.myapplication.classes.api

import android.util.Log
import com.prikhozhayamaria.pmis.deardogs.myapplication.api.KtorApiClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.withContext

class DogsAPIRepository (private val apiClient: KtorApiClient) {

    suspend fun getDogsAPI(): List<DogAPI> {
        return apiClient.getDogsAPI()
    }

}