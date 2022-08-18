package ru.razrabs.design.subcomponents.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun noOverscroll(content: @Composable () -> Unit){
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        content()
    }
}