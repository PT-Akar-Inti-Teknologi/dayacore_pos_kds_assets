package app.dayacore.core.utils

fun Long.roundingPayment(): Long {
    return if (this % 100 >= 50) {
        ((this / 100) + 1) * 100
    } else {
        (this / 100) * 100
    }
}