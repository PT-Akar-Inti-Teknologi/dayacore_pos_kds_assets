package app.dayacore.di.db.usecase

import app.dayacore.domain.usecase.kds.KdsSettingsGetUrlToLoadUseCase
import app.dayacore.domain.usecase.kds.KdsSettingsGetUseCase
import app.dayacore.domain.usecase.kds.KdsSettingsSaveUrlToLoadUseCase
import app.dayacore.domain.usecase.kds.KdsSettingsSaveUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val DBKdsUseCaseModule = module {
    singleOf(::KdsSettingsGetUrlToLoadUseCase)
    singleOf(::KdsSettingsGetUseCase)
    singleOf(::KdsSettingsSaveUrlToLoadUseCase)
    singleOf(::KdsSettingsSaveUseCase)
}