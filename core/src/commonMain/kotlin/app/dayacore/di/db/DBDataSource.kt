package app.dayacore.di.db

import app.dayacore.PreferenceName
import app.dayacore.data.local.repository.kds.KdsDBDataSource
import app.dayacore.data.local.repository.kds.KdsDBDataSourceImpl
import app.dayacore.data.local.repository.pos.ConfigDBDataSource
import app.dayacore.data.local.repository.pos.ConfigDBDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val DBDataSource = module {
    /* POS */
    single<ConfigDBDataSource> {
        ConfigDBDataSourceImpl(
            preference = get(named(PreferenceName.CONFIG)),
            json = get()
        )
    }
    /* KDS */
    single<KdsDBDataSource> {
        KdsDBDataSourceImpl(
            preference = get(named(PreferenceName.KDS_SETTINGS)),
            json = get()
        )
    }
}