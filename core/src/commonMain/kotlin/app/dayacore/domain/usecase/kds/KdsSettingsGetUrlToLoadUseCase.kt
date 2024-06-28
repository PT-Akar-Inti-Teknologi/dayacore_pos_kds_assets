package app.dayacore.domain.usecase.kds

import app.dayacore.data.local.repository.kds.KdsDBDataSource

class KdsSettingsGetUrlToLoadUseCase(
    private val dataSource: KdsDBDataSource,
) {
    suspend fun execute(): String = dataSource.getUrlToLoad()
}