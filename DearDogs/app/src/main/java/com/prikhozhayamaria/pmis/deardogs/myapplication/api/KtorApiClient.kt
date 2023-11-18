package com.prikhozhayamaria.pmis.deardogs.myapplication.api

import android.util.Log
import com.android.volley.BuildConfig
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.api.DogAPI
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.kotlinx.serializer.KotlinxSerializer
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.request.get
import io.ktor.client.request.prepareGet
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.withContext

class KtorApiClient {

         val httpClient = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    explicitNulls = false
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                    prettyPrint = BuildConfig.DEBUG
                    coerceInputValues = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }

        suspend fun getDogsAPI(): List<DogAPI> {
            val url = URLBuilder().apply {
                takeFrom("https://api.api-ninjas.com/v1/dogs") // Replace with your API endpoint
            }

            //return httpClient.get<List<DogAPI>>(url.build());
            //return httpClient.get(url.build()).body();


            return httpClient.get("https://api.api-ninjas.com/v1/dogs").body();


//            try {
//                httpClient
//                    .prepareGet("https://api-ninjas.com/api/dogs")
//                    .execute { response: HttpResponse ->
//                        val remoteItems = response.body<List<DogAPI>>()
//                        remoteItems.map { remote ->
//                            DogAPI(
//                                name = remote.name,
//                                image_link = remote.image_link
//                            )
//                        }
//                    }
//
//            } catch (e: ServerResponseException) {
//                Log.d("Ktor", "Error getting remote items: ${e.response.status.description}")
//                emptyList()
//            } catch (e: ClientRequestException) {
//                Log.d("Ktor", "Error getting remote items: ${e.response.status.description}")
//                emptyList()
//            } catch (e: RedirectResponseException) {
//                Log.d("Ktor", "Error getting remote items: ${e.response.status.description}")
//                emptyList()
//            }
//
//
//        }
        }
}