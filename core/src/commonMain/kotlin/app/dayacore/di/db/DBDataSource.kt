package app.dayacore.di.db

import app.dayacore.PreferenceName
import app.dayacore.data.local.repository.ConfigDBDataSource
import app.dayacore.data.local.repository.ConfigDBDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val DBDataSource = module {
    single<ConfigDBDataSource> {
        ConfigDBDataSourceImpl(
            preference = get(named(PreferenceName.CONFIG)),
            json = get()
        )
    }
}