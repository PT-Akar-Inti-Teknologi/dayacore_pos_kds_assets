package app.dayacore.data.local.repository.kds

import app.dayacore.data.local.model.kds.KdsSettingsData

interface KdsDBDataSource {
    suspend fun saveUrlToLoad(urlToLoad: String): Boolean
    suspend fun saveKdsSettings(kdsSettings: KdsSettingsData): Boolean
    suspend fun getUrlToLoad(): String
    suspend fun getKdsSettings(): KdsSettingsData
}