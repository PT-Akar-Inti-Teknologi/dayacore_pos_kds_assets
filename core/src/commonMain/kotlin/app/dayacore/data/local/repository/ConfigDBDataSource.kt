package app.dayacore.data.local.repository

import app.dayacore.domain.model.request.RabbitMQInitParam
import app.dayacore.domain.model.request.SocketInitParam

interface ConfigDBDataSource {
    suspend fun saveConfig(
        urlToLoad: String,
        branchId: String,
        rabbitMQParam: RabbitMQInitParam,
        socketParam: SocketInitParam
    ): Boolean

    suspend fun getUrlToLoad(): String
    suspend fun getBranchId(): String
    suspend fun getRabbitMQParam(): RabbitMQInitParam
    suspend fun getSocketParam(): SocketInitParam
    suspend fun saveConfigCustomerDisplay(urlToLoad: String): Boolean
    suspend fun getUrlCustomerDisplay(): String
}