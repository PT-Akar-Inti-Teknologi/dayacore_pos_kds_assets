package app.dayacore.di

import app.dayacore.di.db.DBDataSource
import app.dayacore.di.db.usecase.DBConfigUseCaseModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

// This for android side
fun startKoinWithShared(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(getCoreModules())
}

// This for jvm side
fun startKoinWithShared(moduleList: List<Module>) = startKoin {
    modules(getCoreModules())
    modules(moduleList)
}

fun getCoreModules() = listOf(
    // utils
    utilsModule,
    // preference
    preferenceModule,
    // data source
    DBDataSource,
    // use case
    DBConfigUseCaseModule
)