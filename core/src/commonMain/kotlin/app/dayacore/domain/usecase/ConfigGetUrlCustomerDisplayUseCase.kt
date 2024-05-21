package app.dayacore.domain.usecase

import app.dayacore.data.local.repository.ConfigDBDataSource

class ConfigGetUrlCustomerDisplayUseCase(
    private val dataSource: ConfigDBDataSource
) {
    suspend fun execute(): String = dataSource.getUrlCustomerDisplay()
}