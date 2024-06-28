package app.dayacore.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MQBaseTypeDataDto<DATA>(
    @SerialName("type")
    val type: String? = null,
    @SerialName("data")
    val data: DATA? = null,
)
