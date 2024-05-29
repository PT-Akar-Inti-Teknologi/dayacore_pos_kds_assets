package app.dayacore.data.local.repository

import app.dayacore.PreferenceName
import app.dayacore.core.utils.Empty
import app.dayacore.data.local.model.ConfigData
import app.dayacore.domain.model.request.RabbitMQInitParam
import app.dayacore.domain.model.request.SocketInitParam
import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json

class ConfigDBDataSourceImpl(
    private val preference: Settings,
    private val json: Json
) : ConfigDBDataSource {

    private fun getDefaultConfig(): ConfigData {
        return ConfigData(
            urlToLoad = String.Empty,
            branchId = String.Empty,
            rabbitMQParam = RabbitMQInitParam(
                username = String.Empty,
                password = String.Empty,
                hostname = String.Empty,
                port = 0,
                queue = String.Empty
            ),
            socketParam = SocketInitParam(
                hostname = String.Empty,
            ),
            urlCustomerDisplayToLoad = String.Empty
        )
    }

    private fun getConfigData(): ConfigData {
        val configData = preference.getStringOrNull(PreferenceName.CONFIG).orEmpty()
        if (configData.isEmpty()) return getDefaultConfig()

        return try {
            json.decodeFromString(ConfigData.serializer(), configData)
        } catch (e: Exception) {
            getDefaultConfig()
        }
    }

    private fun ConfigData.update(): Boolean {
        // convert
        val json = json.encodeToString(ConfigData.serializer(), this)
        preference.putString(PreferenceName.CONFIG, json)
        // return
        return true
    }

    override suspend fun saveConfig(
        urlToLoad: String,
        branchId: String,
        rabbitMQParam: RabbitMQInitParam,
        socketParam: SocketInitParam
    ): Boolean {
        val configData = getConfigData()
        // update data
        configData.urlToLoad = urlToLoad
        configData.branchId = branchId
        configData.rabbitMQParam = rabbitMQParam
        configData.socketParam = socketParam
        // update
        return configData.update()
    }

    override suspend fun getUrlToLoad(): String {
        return getConfigData().urlToLoad
    }

    override suspend fun getBranchId(): String {
        return getConfigData().branchId
    }

    override suspend fun getRabbitMQParam(): RabbitMQInitParam {
        return getConfigData().rabbitMQParam
    }

    override suspend fun getSocketParam(): SocketInitParam {
        return getConfigData().socketParam
    }

    override suspend fun saveConfigCustomerDisplay(urlToLoad: String): Boolean {
        val configData = getConfigData()
        // update data
        configData.urlCustomerDisplayToLoad = urlToLoad
        // update
        return configData.update()
    }

    override suspend fun getUrlCustomerDisplay(): String {
        return getConfigData().urlCustomerDisplayToLoad
    }
}