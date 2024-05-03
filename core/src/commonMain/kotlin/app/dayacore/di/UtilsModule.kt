package app.dayacore.di

import app.dayacore.core.utils.provideJson
import org.koin.dsl.module

val utilsModule = module {
    single { provideJson() }
}