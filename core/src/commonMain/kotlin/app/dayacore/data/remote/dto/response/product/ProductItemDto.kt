package app.dayacore.data.remote.dto.response.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductItemDto(
    @SerialName("name")
    val name: String? = null,
    @SerialName("price")
    val price: Long? = null,
    @SerialName("stock")
    val stock: Long? = null,
)
