package app.dayacore.domain.usecase.pos

import app.dayacore.data.local.repository.pos.ConfigDBDataSource

class ConfigCustomerDisplaySaveUseCase(
    private val dataSource: ConfigDBDataSource,
) {
    suspend fun execute(
        urlToLoad: String,
    ): Boolean = dataSource.saveConfigCustomerDisplay(urlToLoad = urlToLoad)
}