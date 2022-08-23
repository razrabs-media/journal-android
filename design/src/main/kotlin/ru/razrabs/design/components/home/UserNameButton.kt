package ru.razrabs.design.components.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.razrabs.design.subcomponents.common.ButtonWithoutPadding
import ru.razrabs.design.subcomponents.common.cornerRadius0
import ru.razrabs.design.subcomponents.common.zeroElevation
import ru.razrabs.design.theming.background
import ru.razrabs.design.theming.brand
import ru.razrabs.design.theming.logo
import ru.razrabs.design.theming.styreneBold

@Composable
fun UserNameButton(modifier: Modifier = Modifier, initials: String, onClick: () -> Unit) {
    ButtonWithoutPadding(
        shape = cornerRadius0,
        onClick = onClick,
        elevation = zeroElevation(),
        backgroundColor = brand(),
        modifier = modifier
    ) {
        Text(text = initials, style = styreneBold(color = logo(), size = 16))
    }
}

@Preview
@Composable
fun PreviewUserNameButton() {
    UserNameButton(initials = "LN") {

    }
}