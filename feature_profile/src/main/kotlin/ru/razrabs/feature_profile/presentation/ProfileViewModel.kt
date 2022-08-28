package ru.razrabs.feature_profile.presentation

import android.app.Activity
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel
import ru.razrabs.core.UiStateFlow
import ru.razrabs.core.ext.Err
import ru.razrabs.core.ext.Ok
import ru.razrabs.core.ext.launchIO
import ru.razrabs.feature_auth.domain.AuthGate

@KoinViewModel
class ProfileViewModel(private val authGate: AuthGate) : ViewModel() {

    val state = object :
        UiStateFlow<State>(State(loggedIn = false)) {}

    init {
        updateUserState()
    }

    fun auth(activity: Activity) = launchIO {
        authGate.signIn(activity)
        updateUserState()
    }

    fun signOut() = launchIO {
        authGate.signOut()
        state.update {
            it.copy(loggedIn = false)
        }
    }

    fun updateUserState() = launchIO {
        val result = authGate.getUser()
        if (result != null) {
            state.update {
                it.copy(
                    loggedIn = true,
                    username = result.name,
                    fullUserName = result.name,
                    avatarUrl = result.avatarUrl
                )
            }
        } else {
            state.update {
                it.copy(loggedIn = false)
            }
        }
    }

    @Stable
    data class State(
        val loading: Boolean = false,
        val loggedIn: Boolean,
        val username: String? = null,
        val fullUserName: String? = null,
        val articlesCreated: Int? = null,
        val avatarUrl: String? = null,
        val signupDate: String? = null,
        val timeSinceSignup: String? = null
    )
}