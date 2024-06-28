package app.dayacore.data.local.model.pos

import app.dayacore.domain.model.request.RabbitMQInitParam
import app.dayacore.domain.model.request.SocketInitParam
import kotlinx.serialization.Serializable

@Serializable
data class ConfigData(
    var urlToLoad: String,
    var branchId: String,
    var rabbitMQParam: RabbitMQInitParam,
    var socketParam: SocketInitParam,
    var urlCustomerDisplayToLoad: String,
)