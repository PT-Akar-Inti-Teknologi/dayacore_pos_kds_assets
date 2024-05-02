package app.dayacore.di.db.usecase

import app.dayacore.domain.usecase.ConfigGetRabbitMQParamUseCase
import app.dayacore.domain.usecase.ConfigGetSocketParamUseCase
import app.dayacore.domain.usecase.ConfigGetUrlUseCase
import app.dayacore.domain.usecase.ConfigSaveUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val DBConfigUseCaseModule = module {
    singleOf(::ConfigGetRabbitMQParamUseCase)
    singleOf(::ConfigGetSocketParamUseCase)
    singleOf(::ConfigGetUrlUseCase)
    singleOf(::ConfigSaveUseCase)
}