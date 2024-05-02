package app.dayacore.domain.usecase

import app.dayacore.data.local.repository.ConfigDBDataSource
import app.dayacore.domain.model.request.SocketInitParam

class ConfigGetSocketParamUseCase(
    private val dataSource: ConfigDBDataSource
) {
    suspend fun execute(): SocketInitParam = dataSource.getSocketParam()
}