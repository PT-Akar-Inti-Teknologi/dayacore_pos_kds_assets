package app.dayacore.data.remote.dto.response.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductListDto(
    @SerialName("menu")
    val menu: List<ProductItemDto>? = null,
)
