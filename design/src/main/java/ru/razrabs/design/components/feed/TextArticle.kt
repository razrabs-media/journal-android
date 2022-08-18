package ru.razrabs.design.components.feed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.razrabs.design.VerticalSpacer
import ru.razrabs.design.subcomponents.common.ButtonWithoutPadding
import ru.razrabs.design.subcomponents.common.cornerRadius0
import ru.razrabs.design.theming.logo
import ru.razrabs.design.theming.styreneBold
import kotlin.math.log

@Composable
fun TextArticle(text: String, date: String, onClick: () -> Unit) {
    ButtonWithoutPadding(
        shape = cornerRadius0,
        onClick = onClick,
        borderStroke = BorderStroke(1.dp, logo()),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                style = styreneBold(color = logo(), size = 32, letterSpacing = 0),
                textAlign = TextAlign.Center
            )
            Text(text = date, style = styreneBold(color = logo(), size = 12, letterSpacing = 1))
            VerticalSpacer(height = 4)
        }
    }
}

@Preview
@Composable
fun PreviewTextArticle() {
    TextArticle(
        text = "«В ОБРАЗЕ ЛЕХИ МЕДЬ Я БЫЛ ПОПУЛЯРЕН СРЕДИ СИЛОВИКОВ» — АНДРЕЙ КУЗЬМИН ЗАСТРЯЛ В ЮЖНОЙ АМЕРИКЕ БЕЗ ДЕНЕГ, РАБОТЫ И ВОЗМОЖНОСТИ ВЕРНУТЬСЯ. ВОТ ЕГО ИСТОРИЯ",
        date = "2 ДНЯ НАЗАД"
    ) {

    }

}