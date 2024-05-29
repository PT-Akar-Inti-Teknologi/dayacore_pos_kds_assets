package app.dayacore.di.db.usecase

import app.dayacore.domain.usecase.ConfigCustomerDisplaySaveUseCase
import app.dayacore.domain.usecase.ConfigGetBranchIdUseCase
import app.dayacore.domain.usecase.ConfigGetRabbitMQParamUseCase
import app.dayacore.domain.usecase.ConfigGetSocketParamUseCase
import app.dayacore.domain.usecase.ConfigGetUrlCustomerDisplayUseCase
import app.dayacore.domain.usecase.ConfigGetUrlUseCase
import app.dayacore.domain.usecase.ConfigSaveUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val DBConfigUseCaseModule = module {
    singleOf(::ConfigCustomerDisplaySaveUseCase)
    singleOf(::ConfigGetBranchIdUseCase)
    singleOf(::ConfigGetRabbitMQParamUseCase)
    singleOf(::ConfigGetSocketParamUseCase)
    singleOf(::ConfigGetUrlCustomerDisplayUseCase)
    singleOf(::ConfigGetUrlUseCase)
    singleOf(::ConfigSaveUseCase)
}