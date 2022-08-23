package ru.razrabs.feature_profile.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.razrabs.design.components.common.LoginViaGithubButton
import ru.razrabs.design.components.profile.ProfileInfoBlock
import ru.razrabs.design.components.profile.ProfileTopBar

@Composable
fun ProfileScreen(onBackAction: () -> Unit) {
    ProfileContent(onBackAction = onBackAction)
}

@Composable
fun ProfileContent(onBackAction: () -> Unit) {
    Scaffold(topBar = {
        ProfileTopBar(onBackAction = onBackAction)
    },
        bottomBar = {
            LoginViaGithubButton {

            }
        }) {
        ProfileInfoBlock()
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewProfile() {
    ProfileContent(onBackAction = {})
}