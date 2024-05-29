package app.dayacore.domain.usecase

import app.dayacore.data.local.repository.ConfigDBDataSource
import app.dayacore.domain.model.request.RabbitMQInitParam
import app.dayacore.domain.model.request.SocketInitParam

class ConfigSaveUseCase(
    private val dataSource: ConfigDBDataSource
) {
    suspend fun execute(
        urlToLoad: String,
        branchId: String,
        rabbitMQParam: RabbitMQInitParam,
        socketParam: SocketInitParam
    ): Boolean = dataSource.saveConfig(
        urlToLoad = urlToLoad,
        branchId = branchId,
        rabbitMQParam = rabbitMQParam,
        socketParam = socketParam
    )
}