package app.dayacore.domain.usecase

import app.dayacore.data.local.repository.ConfigDBDataSource
import app.dayacore.domain.model.request.RabbitMQInitParam

class ConfigGetRabbitMQParamUseCase(
    private val dataSource: ConfigDBDataSource
) {
    suspend fun execute(): RabbitMQInitParam = dataSource.getRabbitMQParam()
}