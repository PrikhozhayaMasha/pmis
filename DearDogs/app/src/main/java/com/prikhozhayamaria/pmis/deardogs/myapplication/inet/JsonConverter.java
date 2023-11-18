package com.prikhozhayamaria.pmis.deardogs.myapplication.inet;

//import io.ktor.client.features.json.JsonSerializer
//import io.ktor.http.ContentType
//import io.ktor.http.content.TextContent
//import io.ktor.http.Headers
//import kotlinx.serialization.Serializable
//import kotlinx.serialization.json.Json
//
//class JsonConverter : ContentConverter {
//private val json = Json { /* конфигурация JSON */ }
//
//        override suspend fun marshal(content: Any): TextContent {
//        val jsonContent = json.encodeToString(content)
//        return TextContent(
//        text = jsonContent,
//        contentType = ContentType.Application.Json
//        )
//        }
//
//        override suspend fun unmarshal(content: TextContent): Any {
//        val jsonContent = content.text
//        // Десериализация JSON
//        return json.decodeFromString(jsonContent)
//        }
//
//        override fun modifyHeaders(headers: Headers) {
//        // Здесь можно изменять заголовки запроса перед отправкой
//        }
//        }
