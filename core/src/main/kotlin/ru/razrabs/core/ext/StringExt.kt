package ru.razrabs.core.ext

import android.content.Context
import ru.razrabs.core.R
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

private const val serverInputPattern = "yyyy-MM-dd'T'HH:mm:ss"

fun Context.parseDate(date: String, fullFormat: Boolean = true): String {
    val ldt = LocalDateTime.parse(date)
    val zoneId: ZoneId = ZoneId.of("Europe/Moscow")
    val zdt: ZonedDateTime = ldt.atZone(zoneId)
    val calendar = Calendar.getInstance()

    val thisYear = zdt.year == calendar.get(Calendar.YEAR)

    val thisWeek =
        (thisYear && zdt.month.value == calendar.get(Calendar.MONTH) + 1 && zdt.dayOfMonth in calendar.get(
            Calendar.DAY_OF_MONTH
        ) - 7..calendar.get(Calendar.DAY_OF_MONTH))

    val result = if (thisWeek) {
        val today = calendar.get(Calendar.DAY_OF_MONTH) == zdt.dayOfMonth
        if (today) {
            val thisHour = calendar.get(Calendar.HOUR_OF_DAY) == zdt.hour
            val thisMinute = calendar.get(Calendar.MINUTE) == zdt.minute
            if (thisHour) {
                if (thisMinute) {
                    getProperString(
                        calendar.get(Calendar.SECOND),
                        zdt.second,
                        if (fullFormat) R.plurals.seconds_full else R.plurals.seconds_small
                    )
                } else {
                    getProperString(
                        calendar.get(Calendar.MINUTE),
                        zdt.minute,
                        if (fullFormat) R.plurals.minutes_full else R.plurals.minutes_small
                    )
                }
            } else {
                getProperString(
                    calendar.get(Calendar.HOUR_OF_DAY),
                    zdt.hour,
                    if (fullFormat) R.plurals.hours_full else R.plurals.hours_small
                )
            }
        } else {
            getProperString(
                calendar.get(Calendar.DAY_OF_MONTH),
                zdt.dayOfMonth,
                if (fullFormat) R.plurals.days_full else R.plurals.days_small
            )
        }
    } else if (thisYear) {
        date.getDateInAnotherFormat(serverInputPattern, if(fullFormat) "d MMMM" else "d MMM")
    } else {
        date.getDateInAnotherFormat(serverInputPattern, if(fullFormat) "d MMMM yyyy" else "d MMM yy")
    }
    return result
}

private fun Context.getProperString(start: Int, end: Int, resId: Int) =
    resources.getQuantityString(resId, start - end, start - end)

private fun String.getDateInAnotherFormat(inputFormat: String, outputFormat: String): String =
    SimpleDateFormat(inputFormat, Locale.getDefault()).parse(this)
        ?.let { SimpleDateFormat(outputFormat, Locale.getDefault()).format(it) } ?: ""