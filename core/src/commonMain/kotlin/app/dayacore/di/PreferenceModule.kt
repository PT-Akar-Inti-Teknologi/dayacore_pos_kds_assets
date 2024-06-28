package app.dayacore.di

import app.dayacore.PreferenceName
import app.dayacore.platform.getPlatform
import org.koin.core.qualifier.named
import org.koin.dsl.module

val preferenceModule = module {
    single(named(PreferenceName.CONFIG)) {
        getPlatform().getEncryptedPreference(PreferenceName.CONFIG)
    }
    single(named(PreferenceName.KDS_SETTINGS)) {
        getPlatform().getEncryptedPreference(PreferenceName.KDS_SETTINGS)
    }
}