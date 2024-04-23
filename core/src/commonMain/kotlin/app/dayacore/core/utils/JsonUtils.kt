package app.dayacore.core.utils

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json


private val json = Json { ignoreUnknownKeys = true }

fun <T> T.toJson(serializer: SerializationStrategy<T>): String =
    json.encodeToString(serializer, this)

fun <T> String.fromJson(deserializer: DeserializationStrategy<T>): T =
    json.decodeFromString(deserializer, this)
