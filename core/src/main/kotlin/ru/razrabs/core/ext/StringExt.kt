package ru.razrabs.core.ext

import android.content.Context
import ru.razrabs.core.R
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

private const val serverInputPattern = "yyyy-MM-dd'T'HH:mm:ss"

fun Context.parseDate(date: String): String {
    val ldt = LocalDateTime.parse(date)
    val zoneId: ZoneId = ZoneId.of("Europe/Moscow")
    val zdt: ZonedDateTime = ldt.atZone(zoneId)
    val calendar = Calendar.getInstance()

    val thisYear = zdt.year == calendar.get(Calendar.YEAR)

    val thisWeek =
        (thisYear && zdt.month.value == calendar.get(Calendar.MONTH) + 1 && zdt.dayOfMonth in calendar.get(
            Calendar.DAY_OF_MONTH
        ) - 7..calendar.get(Calendar.DAY_OF_MONTH))

    val result =  if (thisWeek) {
        val today = calendar.get(Calendar.DAY_OF_MONTH) == zdt.dayOfMonth
        if (today) {
            val thisHour = calendar.get(Calendar.HOUR_OF_DAY) == zdt.hour
            val thisMinute = calendar.get(Calendar.MINUTE) == zdt.minute
            if (thisHour) {
                if (thisMinute) {
                    getProperString(calendar.get(Calendar.SECOND), zdt.second, R.plurals.seconds)
                } else {
                    getProperString(calendar.get(Calendar.MINUTE), zdt.minute, R.plurals.minutes)
                }
            } else {
                getProperString(calendar.get(Calendar.HOUR_OF_DAY), zdt.hour, R.plurals.hours)
            }
        } else {
            getProperString(calendar.get(Calendar.DAY_OF_MONTH), zdt.dayOfMonth, R.plurals.days)
        }
    } else if (thisYear) {
        date.getDateInAnotherFormat(serverInputPattern, "d MMMM")
    } else {
        date.getDateInAnotherFormat(serverInputPattern, "d MMMM yyyy")
    }
    return result
}

private fun Context.getProperString(start: Int, end: Int, resId: Int) =
    resources.getQuantityString(resId, start - end, start - end)

private fun String.getDateInAnotherFormat(inputFormat: String, outputFormat: String): String =
    SimpleDateFormat(inputFormat, Locale.getDefault()).parse(this)
        ?.let { SimpleDateFormat(outputFormat, Locale.getDefault()).format(it) } ?: ""