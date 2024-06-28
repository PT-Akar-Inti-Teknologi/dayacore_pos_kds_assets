package app.dayacore.di.db.usecase

import app.dayacore.domain.usecase.pos.ConfigCustomerDisplaySaveUseCase
import app.dayacore.domain.usecase.pos.ConfigGetBranchIdUseCase
import app.dayacore.domain.usecase.pos.ConfigGetRabbitMQParamUseCase
import app.dayacore.domain.usecase.pos.ConfigGetSocketParamUseCase
import app.dayacore.domain.usecase.pos.ConfigGetUrlCustomerDisplayUseCase
import app.dayacore.domain.usecase.pos.ConfigGetUrlUseCase
import app.dayacore.domain.usecase.pos.ConfigSaveUseCase
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