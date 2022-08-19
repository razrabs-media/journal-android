package ru.razrabs.feature_feed.presentation.preview

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel
import ru.razrabs.core.UiStateFlow
import ru.razrabs.core.ext.Err
import ru.razrabs.core.ext.Ok
import ru.razrabs.core.ext.launchIO
import ru.razrabs.feature_feed.domain.LoadByFeed
import ru.razrabs.feature_feed.domain.LoadFrontPage
import ru.razrabs.feature_feed.domain.LoadPost
import ru.razrabs.network.models.front_page.FrontPage

@KoinViewModel
class FeedViewModel(
    private val loadByFeed: LoadByFeed,
    private val loadFrontPage: LoadFrontPage
) : ViewModel() {

    val state = object :
        UiStateFlow<State>(State()) {}

    init {
        updateFrontPage(false)
    }

    fun updateFrontPage(force: Boolean) {
        launchIO {
            state.update { state->
                state.copy(isRefreshing = true)
            }
            when (val result = loadFrontPage(force)) {
                is Ok -> {
                    state.update { state ->
                        state.copy(frontPage = result.value, isRefreshing = false)
                    }
                }
                is Err -> {

                }
            }
        }
    }


    @Stable
    data class State(val frontPage: FrontPage? = null, val isRefreshing: Boolean = true)
}