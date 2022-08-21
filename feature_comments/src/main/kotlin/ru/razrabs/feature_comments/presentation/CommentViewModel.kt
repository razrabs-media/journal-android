package ru.razrabs.feature_comments.presentation

import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel
import ru.razrabs.core.UiStateFlow
import ru.razrabs.core.ext.launchIO
import ru.razrabs.feature_comments.domain.CreateComment
import ru.razrabs.feature_comments.domain.LoadComments
import ru.razrabs.feature_feed.presentation.preview.FeedViewModel
import ru.razrabs.network.models.post.Author
import ru.razrabs.network.models.post.Comment

@KoinViewModel
class CommentViewModel(
    private val loadCommentsUseCase: LoadComments,
    private val createCommentUseCase: CreateComment
) : ViewModel() {

    val state = object :
        UiStateFlow<State>(State(listOf())) {}

    init {
        launchIO {
            state.update {
                it.copy(
                    comments = getTestList()
                )
            }
        }
    }

    data class State(val comments: List<Comment>)

    companion object {
        fun getTestList() = listOf(
            Comment(
                "asdasd",
                Author("asd", "https://avatars.githubusercontent.com/philippranzhin", "Test1"),
                null,
                "Ой как приятно видеть на своём ресурсе технические статьи, словами не передать",
                "1 д"
            ),
            Comment(
                "asdasd1",
                Author("asd", "https://avatars.githubusercontent.com/philippranzhin", "Test1"),
                null,
                "Ой как приятно видеть на своём ресурсе технические статьи, словами не передать",
                "1 д"
            ),
            Comment(
                "asdasd2",
                Author("asd", "https://avatars.githubusercontent.com/philippranzhin", "Test1"),
                null,
                "Ой как приятно видеть на своём ресурсе технические статьи, словами не передать",
                "1 д"
            ),
            Comment(
                "asdasd3",
                Author("asd", "https://avatars.githubusercontent.com/philippranzhin", "Test1"),
                null,
                "Ой как приятно видеть на своём ресурсе технические статьи, словами не передать",
                "1 д"
            ),
            Comment(
                "asdasd4",
                Author("asd", "https://avatars.githubusercontent.com/philippranzhin", "Test1"),
                null,
                "Ой как приятно видеть на своём ресурсе технические статьи, словами не передать",
                "1 д"
            ),
            Comment(
                "asdasd5",
                Author("asd", "https://avatars.githubusercontent.com/philippranzhin", "Test1"),
                null,
                "Ой как приятно видеть на своём ресурсе технические статьи, словами не передать",
                "1 д"
            ),
        )
    }
}