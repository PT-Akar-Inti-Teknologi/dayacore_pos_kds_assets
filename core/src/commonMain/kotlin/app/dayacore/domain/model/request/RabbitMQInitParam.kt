package app.dayacore.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RabbitMQInitParam(
    val username: String,
    val password: String,
    val hostname: String,
    val port: Int,
    val queue: String,
)
