package ru.razrabs.feature_home.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    HomeContent()
}

@Composable
fun HomeContent() {
    Scaffold(topBar = {}) {

    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeContent()
}