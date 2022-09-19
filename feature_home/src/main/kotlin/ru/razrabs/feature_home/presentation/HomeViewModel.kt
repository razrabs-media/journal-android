package ru.razrabs.feature_home.presentation

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.annotation.KoinViewModel
import ru.razrabs.core.UiStateFlow
import ru.razrabs.core.ext.launchIO
import ru.razrabs.feature_auth.domain.GetUser
import ru.razrabs.feature_auth.domain.GetUserFlow

@KoinViewModel
class HomeViewModel(private val getUserFlow: GetUserFlow) : ViewModel() {

    val state = object :
        UiStateFlow<State>(State()) {}

    init {
        launchIO {
            getUserFlow().collectLatest { user ->
                state.update {
                    it.copy(initials = if (user != null) resolveInitials(user.name) else null)
                }
            }
        }
    }

    private fun resolveInitials(fullName: String): String {
        val elements = fullName.split(' ').mapNotNull { it.firstOrNull() }
        val sb = StringBuilder()
        elements.forEach {
            if (!it.isWhitespace()) {
                sb.append(it)
            }
        }
        return sb.toString()
    }

    @Stable
    data class State(val initials: String? = null)
}