package ru.razrabs.design.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.razrabs.core.R
import ru.razrabs.design.theming.background
import ru.razrabs.design.theming.jbBold
import ru.razrabs.design.theming.styreneBold

@Composable
fun HomeAppBar() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = stringResource(id = R.string.razrabs),
            style = styreneBold(color = background(), size = 24)
        )
    }
}

@Preview
@Composable
fun PreviewHomeAppBar() {
    HomeAppBar()
}