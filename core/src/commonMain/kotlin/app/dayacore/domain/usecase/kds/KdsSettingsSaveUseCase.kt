package app.dayacore.domain.usecase.kds

import app.dayacore.data.local.model.kds.KdsSettingsData
import app.dayacore.data.local.repository.kds.KdsDBDataSource

class KdsSettingsSaveUseCase(
    private val dataSource: KdsDBDataSource,
) {
    suspend fun execute(kdsSettings: KdsSettingsData): Boolean =
        dataSource.saveKdsSettings(kdsSettings = kdsSettings)
}