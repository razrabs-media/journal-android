package ru.razrabs.feature_auth.data

import android.content.Context
import org.koin.core.annotation.Single
import ru.razrabs.feature_auth.domain.AdditionalInfoSaver

@Single
class AdditionalInfoSaverImpl(context: Context): AdditionalInfoSaver {

    private val sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE)
    private val edit = sp.edit()

    override var additionalInfo: String?
        get() = sp.getString(USERNAME_KEY, null)
        set(value) {
            edit.putString(USERNAME_KEY, value).apply()
        }

    companion object {
        private const val SP_KEY = "ADDITIONAL_USER_INFO"
        private const val USERNAME_KEY = "USERNAME_KEY"
    }
}