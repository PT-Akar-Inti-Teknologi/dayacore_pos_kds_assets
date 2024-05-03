package app.dayacore.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MQBaseTypeDto(
    @SerialName("type")
    val type: String? = null
)
