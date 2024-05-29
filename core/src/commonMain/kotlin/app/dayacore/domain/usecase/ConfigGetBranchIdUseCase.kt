package app.dayacore.domain.usecase

import app.dayacore.data.local.repository.ConfigDBDataSource

class ConfigGetBranchIdUseCase(
    private val dataSource: ConfigDBDataSource
) {
    suspend fun execute(): String = dataSource.getBranchId()
}