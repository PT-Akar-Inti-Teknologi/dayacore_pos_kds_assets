package app.dayacore.platform

import com.russhwolf.settings.Settings

interface Platform {

    fun getEncryptedPreference(preferenceName: String): Settings

}

expect fun getPlatform(): Platform