package ru.razrabs.feature_auth.data

import android.app.Activity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

    private val _userFlow = MutableStateFlow<User?>(null)
    private val flow = _userFlow.asStateFlow()

    private val ioScope = CoroutineScope(Dispatchers.IO)

    init {
        ioScope.launch {
            getUser()?.let {
                _userFlow.emit(it)
            }
        }
    }

    override suspend fun auth(activity: Activity): Result<User, Exception> {
        val result = authHandler.auth(activity)
        if (result is Ok) {
            additionalInfoSaver.additionalInfo = result.value.username
            _userFlow.emit(result.value)
        }
        return result
    }

    override suspend fun getUser(): User? {
        val user = authHandler.getUser()
        return if (user != null) {
            val additionalInfo = additionalInfoSaver.additionalInfo
            val mappedUser = user.map(additionalInfo)
            _userFlow.emit(mappedUser)
            mappedUser
        } else {
            null
        }
    }

    override fun getUserFlow(): Flow<User?> = flow

    override suspend fun signOut() {
        _userFlow.emit(null)
        authHandler.signOut()
        additionalInfoSaver.additionalInfo = null
    }
}