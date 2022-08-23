package ru.razrabs.feature_profile.presentation

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel
import ru.razrabs.core.UiStateFlow

@KoinViewModel
class ProfileViewModel : ViewModel() {

    val state = object :
        UiStateFlow<State>(State(loggedIn = false)) {}

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