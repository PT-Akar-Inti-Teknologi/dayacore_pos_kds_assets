package app.dayacore.data.local.repository.kds

import app.dayacore.PreferenceName
import app.dayacore.core.utils.Empty
import app.dayacore.data.local.model.kds.KdsSettingsData
import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json

class KdsDBDataSourceImpl(
    private val preference: Settings,
    private val json: Json,
) : KdsDBDataSource {

    private fun getDefaultConfig() = KdsSettingsData(
        urlToLoad = String.Empty,
        viewMode = String.Empty,
        visitPurposeId = emptyList(),
        timeFirstWarning = 5,
        timeSecondWarning = 10,
        stationsId = emptyList(),
        printingStationId = emptyList()
    )

    private fun getKdsSettingsData(): KdsSettingsData {
        val kdsSetting = preference.getStringOrNull(PreferenceName.KDS_SETTINGS).orEmpty()
        if (kdsSetting.isEmpty()) return getDefaultConfig()

        return try {
            json.decodeFromString(KdsSettingsData.serializer(), kdsSetting)
        } catch (e: Exception) {
            getDefaultConfig()
        }
    }

    private fun KdsSettingsData.update(): Boolean {
        // convert
        val json = json.encodeToString(KdsSettingsData.serializer(), this)
        preference.putString(PreferenceName.KDS_SETTINGS, json)
        // return
        return true
    }

    override suspend fun saveUrlToLoad(urlToLoad: String): Boolean {
        val kdsSettingsData = getKdsSettingsData()
        // update data
        kdsSettingsData.urlToLoad = urlToLoad
        // update
        return kdsSettingsData.update()
    }

    override suspend fun saveKdsSettings(kdsSettings: KdsSettingsData): Boolean {
        val kdsSettingsData = getKdsSettingsData()
        // update data
        kdsSettingsData.viewMode = kdsSettings.viewMode
        kdsSettingsData.visitPurposeId = kdsSettings.visitPurposeId
        kdsSettingsData.timeFirstWarning = kdsSettings.timeFirstWarning
        kdsSettingsData.timeSecondWarning = kdsSettings.timeSecondWarning
        kdsSettingsData.stationsId = kdsSettings.stationsId
        kdsSettingsData.printingStationId = kdsSettings.printingStationId
        // update
        return kdsSettingsData.update()
    }

    override suspend fun getUrlToLoad(): String {
        return getKdsSettingsData().urlToLoad
    }

    override suspend fun getKdsSettings(): KdsSettingsData {
        return getKdsSettingsData()
    }
}