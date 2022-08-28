package ru.razrabs.feature_auth.data

import android.app.Activity
import org.koin.core.annotation.Single
import ru.razrabs.core.ext.Ok
import ru.razrabs.core.ext.Result
import ru.razrabs.feature_auth.domain.AdditionalInfoSaver
import ru.razrabs.feature_auth.domain.AuthHandler
import ru.razrabs.feature_auth.domain.AuthRepository
import ru.razrabs.feature_auth.domain.User

@Single
class AuthRepositoryImpl(
    private val authHandler: AuthHandler,
    private val additionalInfoSaver: AdditionalInfoSaver
) : AuthRepository {
    override suspend fun auth(activity: Activity): Result<User, Exception> {
        val result = authHandler.auth(activity)
        if (result is Ok) {
            additionalInfoSaver.additionalInfo = result.value.usernameUrl
        }
        return result
    }

    override suspend fun getUser(): User? {
        val user = authHandler.getUser()
        return if (user != null) {
            val additionalInfo = additionalInfoSaver.additionalInfo
            user.map(additionalInfo)
        } else {
            null
        }
    }

    override suspend fun signOut() {
        authHandler.signOut()
        additionalInfoSaver.additionalInfo = null
    }
}