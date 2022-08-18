package ru.razrabs.design.theming

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.razrabs.design.R


val Styrene = FontFamily(
    Font(R.font.styrene_bold, weight = FontWeight.Bold),
    Font(R.font.styrene_medium, weight = FontWeight.Medium),
    Font(R.font.styrene_regular, weight = FontWeight.Normal)
)

val JbMono = FontFamily(
    Font(R.font.jbmono_bold, weight = FontWeight.Bold),
    Font(R.font.jbmono_regular, weight = FontWeight.Normal)
)

fun styreneBold(color: Color, size: Int) =
    TextStyle(
        fontSize = size.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        fontFamily = Styrene
    )

fun styreneMedium(color: Color, size: Int) =
    TextStyle(
        fontSize = size.sp,
        fontWeight = FontWeight.Medium,
        color = color,
        fontFamily = Styrene
    )

fun styreneRegular(color: Color, size: Int) =
    TextStyle(
        fontSize = size.sp,
        fontWeight = FontWeight.Normal,
        color = color,
        fontFamily = Styrene
    )

fun jbBold(color: Color, size: Int) =
    TextStyle(
        fontSize = size.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        fontFamily = JbMono
    )

fun jbRegular(color: Color, size: Int) =
    TextStyle(
        fontSize = size.sp,
        fontWeight = FontWeight.Normal,
        color = color,
        fontFamily = JbMono
    )