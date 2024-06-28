package app.dayacore.domain.usecase.kds

import app.dayacore.data.local.model.kds.KdsSettingsData
import app.dayacore.data.local.repository.kds.KdsDBDataSource

class KdsSettingsGetUseCase(
    private val dataSource: KdsDBDataSource,
) {
    suspend fun execute(): KdsSettingsData = dataSource.getKdsSettings()
}