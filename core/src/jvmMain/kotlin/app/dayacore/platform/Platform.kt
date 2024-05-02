package app.dayacore.platform

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import org.koin.core.component.KoinComponent
import java.util.prefs.Preferences

class JvmPlatform : Platform, KoinComponent {

    override fun getEncryptedPreference(preferenceName: String): Settings {
        val delegate = Preferences.systemRoot()
        return PreferencesSettings(delegate = delegate)
    }

}

actual fun getPlatform(): Platform = JvmPlatform()