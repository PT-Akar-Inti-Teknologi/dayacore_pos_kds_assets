package app.dayacore.domain.usecase.pos

import app.dayacore.data.local.repository.pos.ConfigDBDataSource
import app.dayacore.domain.model.request.SocketInitParam

class ConfigGetSocketParamUseCase(
    private val dataSource: ConfigDBDataSource,
) {
    suspend fun execute(): SocketInitParam = dataSource.getSocketParam()
}