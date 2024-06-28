package app.dayacore.domain.usecase.pos

import app.dayacore.data.local.repository.pos.ConfigDBDataSource

class ConfigGetUrlUseCase(
    private val dataSource: ConfigDBDataSource,
) {
    suspend fun execute(): String = dataSource.getUrlToLoad()
}