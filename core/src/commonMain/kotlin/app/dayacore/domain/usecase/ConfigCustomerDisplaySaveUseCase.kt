package app.dayacore.domain.usecase

import app.dayacore.data.local.repository.ConfigDBDataSource

class ConfigCustomerDisplaySaveUseCase(
    private val dataSource: ConfigDBDataSource
) {
    suspend fun execute(
        urlToLoad: String,
    ): Boolean = dataSource.saveConfigCustomerDisplay(urlToLoad = urlToLoad)
}