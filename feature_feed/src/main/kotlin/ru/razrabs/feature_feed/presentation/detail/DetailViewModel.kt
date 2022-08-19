package ru.razrabs.feature_feed.presentation.detail

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel
import ru.razrabs.core.UiStateFlow
import ru.razrabs.core.ext.Err
import ru.razrabs.core.ext.Ok
import ru.razrabs.core.ext.launchIO
import ru.razrabs.feature_feed.domain.LoadPost
import ru.razrabs.feature_feed.presentation.preview.FeedViewModel
import ru.razrabs.network.models.post.Post

@KoinViewModel
class DetailViewModel(private val loadPost: LoadPost) : ViewModel() {

    val state = object :
        UiStateFlow<State>(State()) {}

    fun populateState(postUid: String) {
        launchIO {
            when (val result = loadPost(postUid)) {
                is Ok -> {
                    state.update { it.copy(post = result.value) }
                }
                is Err -> {

                }
            }
        }
    }

    @Stable
    data class State(val post: Post? = null)

}