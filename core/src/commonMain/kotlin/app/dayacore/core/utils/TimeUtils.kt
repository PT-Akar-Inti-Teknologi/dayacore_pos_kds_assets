package app.dayacore.core.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun dateNowToDDMMYYYY(separator: String = "/", defaultValue: String = "-"): String {
    return try {
        val localDateTime = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())

        val month = localDateTime.monthNumber.takeIf { it > 9 } ?: "0${localDateTime.monthNumber}"

        "${localDateTime.dayOfMonth}$separator$month$separator${localDateTime.year}"
    } catch (e: Exception) {
        defaultValue
    }
}

fun String.dateToDDMMMYYYY(separator: String = "/", defaultValue: String = "-"): String {
    if (this.isEmpty())
        return defaultValue

    return try {
        val localDateTime = Instant.parse(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())

        "${localDateTime.dayOfMonth}$separator${
            localDateTime.month.name.toCapitalized().substring(0, 3)
        }$separator${localDateTime.year}"
    } catch (e: Exception) {
        defaultValue
    }
}

fun String.dateToDDMMMNameYYYY(separator: String = "/", defaultValue: String = "-"): String {
    if (this.isEmpty())
        return defaultValue

    return try {
        val localDateTime = Instant.parse(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())

        "${localDateTime.dayOfMonth}$separator${
            localDateTime.month.name.toCapitalized()
        }$separator${localDateTime.year}"
    } catch (e: Exception) {
        defaultValue
    }
}

fun String.dateToHHMM(separator: String = ":", defaultValue: String = "-"): String {
    if (this.isEmpty())
        return defaultValue

    return try {
        val localDateTime = Instant.parse(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())
        val hour = localDateTime.hour.takeIf { localDateTime.hour.toString().length == 2 }
            ?: "0${localDateTime.hour}"
        val minute = localDateTime.minute.takeIf { localDateTime.minute.toString().length == 2 }
            ?: "0${localDateTime.minute}"
        val meridiem = "AM".takeIf { localDateTime.hour <= 12 } ?: "PM"
        "$hour$separator$minute$meridiem"
    } catch (e: Exception) {
        defaultValue
    }
}

fun String.isComingSoonDate(): Boolean {
    val currentInstant: Instant = Clock.System.now()
    val currentLocalDate = currentInstant.toLocalDateTime(TimeZone.currentSystemDefault())
    val specificDate = Instant.parse(this).toLocalDateTime(TimeZone.currentSystemDefault())

    return currentLocalDate < specificDate
}

fun isOnGoingDate(startDate: String, endDate: String): Boolean {
    val currentInstant: Instant = Clock.System.now()
    val currentLocalDate = currentInstant.toLocalDateTime(TimeZone.currentSystemDefault())
    val startInstantDate = Instant.parse(startDate).toLocalDateTime(TimeZone.currentSystemDefault())
    val endInstantDate = Instant.parse(endDate).toLocalDateTime(TimeZone.currentSystemDefault())

    return startInstantDate <= currentLocalDate && currentLocalDate < endInstantDate
}