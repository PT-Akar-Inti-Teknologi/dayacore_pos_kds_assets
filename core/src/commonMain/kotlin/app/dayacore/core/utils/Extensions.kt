package app.dayacore.core.utils

fun String.formatPhoneNumber(): String {
    val formattedNumber = StringBuilder(this)
    for (i in 3 until this.length step 4) {
        formattedNumber.insert(i, " ")
    }
    return formattedNumber.toString()
}

val String.Companion.Empty
    inline get() = ""

fun String.toCapitalized(): String {
    if (this.isEmpty()) return ""

    val words = this.split(" ")
    val capitalizedWords = words.map { it.lowercase().replaceFirstChar(Char::titlecase) }
    return capitalizedWords.joinToString(" ")
}

fun String.isValidIPv4(): Boolean {
    val ipv4Regex =
        Regex("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
    return ipv4Regex.matches(this)
}