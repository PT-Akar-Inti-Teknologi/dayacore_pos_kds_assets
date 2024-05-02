package app.dayacore.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SocketInitParam(
    val hostname: String,
    val port: Int = 9002
)
