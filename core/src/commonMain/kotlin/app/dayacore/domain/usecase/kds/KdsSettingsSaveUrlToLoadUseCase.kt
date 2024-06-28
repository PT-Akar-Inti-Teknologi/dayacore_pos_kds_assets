package app.dayacore.domain.usecase.kds

import app.dayacore.data.local.repository.kds.KdsDBDataSource

class KdsSettingsSaveUrlToLoadUseCase(
    private val dataSource: KdsDBDataSource,
) {
    suspend fun execute(urlToLoad: String): Boolean =
        dataSource.saveUrlToLoad(urlToLoad = urlToLoad)
}