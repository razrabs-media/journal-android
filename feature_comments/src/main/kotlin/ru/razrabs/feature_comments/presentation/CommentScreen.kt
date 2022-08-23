package ru.razrabs.feature_comments.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import ru.razrabs.core.ext.parseDate
import ru.razrabs.design.components.comment.CommentBottomBar
import ru.razrabs.design.components.comment.CommentItem
import ru.razrabs.design.components.comment.CommentTopBar

@Composable
fun CommentScreen(
    vm: CommentViewModel = getViewModel(),
    onBackAction: () -> Unit,
    postUid: String,
    name: String
) {
    val state = vm.state.uiState.collectAsState().value
    LaunchedEffect(postUid) {
        vm.initialize(postUid, name)
    }
    CommentContent(state = state, onBackAction = onBackAction)
}

@Composable
fun CommentContent(state: CommentViewModel.State, onBackAction: () -> Unit) {
    val ctx = LocalContext.current

    Scaffold(topBar = {
        CommentTopBar(commentCount = 4, onBackAction = onBackAction, title = state.name)
    }, bottomBar = {
        CommentBottomBar(onSend = {}, onLogin = {}, loggedIn = false, avatar = null)
    }) {
        LazyColumn() {
            items(state.comments) {
                CommentItem(
                    username = it.author.name ?: "",
                    createdAt = ctx.parseDate(date = it.createdAt, fullFormat = false),
                    content = it.content,
                    avatar = it.author.avatarUrl
                ) {

                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewComment() {
//    CommentContent(CommentViewModel.State(getTestList()), {})
}