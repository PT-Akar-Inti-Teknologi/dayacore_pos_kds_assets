package app.dayacore.core.extensions

import androidx.compose.runtime.Immutable
import app.dayacore.domain.model.response.Pagination
import app.dayacore.domain.model.response.StatusResponse
import app.dayacore.domain.utils.ResultWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime

fun String.formatCurrency(): String {
    // validate empty
    if (this.isEmpty()) return this

    val decimalSeparator = ','
    val groupingSeparator = '.'

    val formattedAmount = StringBuilder()

    // Formatting the whole number part
    val amount = this.toDouble()
    val wholeNumber = this
    var index = wholeNumber.length - 1
    var count = 0

    while (index >= 0) {
        formattedAmount.append(wholeNumber[index])
        count++
        if (count % 3 == 0 && index != 0) {
            formattedAmount.append(groupingSeparator)
        }
        index--
    }

    formattedAmount.reverse()

    // Formatting the decimal part
    val decimalPart = amount - amount.toInt()
    if (decimalPart > 0) {
        formattedAmount.append(decimalSeparator)
        formattedAmount.append(decimalPart.toString().substring(2))
    }

    return formattedAmount.toString()
}

fun Pagination.canLoadMore(): Boolean {
    return this.currentPage < this.totalPage
}

suspend inline fun <T : Any> Flow<ResultWrapper<T>>.collectWithCallbacks(
    crossinline onStart: () -> Unit,
    crossinline onSuccess: suspend (T) -> Unit,
    crossinline onFailed: suspend (StatusResponse?) -> Unit,
    crossinline onFinally: () -> Unit,
) {
    this.onStart {
        onStart.invoke()
    }.collect { result ->
        when (result) {
            is ResultWrapper.Success -> {
                onSuccess.invoke(result.data)
                onFinally.invoke()
            }

            is ResultWrapper.Error -> {
                onFailed.invoke(result.statusResponse)
                onFinally.invoke()
            }
        }
    }
}

/** Use @Immutable Annotations
This will tell compose compiler the list is immutable and avoid unnecessary recompositions.
Use this when your list doesn’t change frequently **/
@Immutable
private class ImmutableList<T>(val list: List<T>)

fun <T> List<T>.toImmutableList(): List<T> {
    return ImmutableList(this).list
}

// scheduler running repeatedly action
suspend fun scheduleRepeatedly(
    delayTimeMillis: Long,
    delayAction: suspend CoroutineScope.() -> Unit
) =
    coroutineScope {
        while (true) {
            delay(timeMillis = delayTimeMillis)
            launch { delayAction() }
        }
    }

// convert time from seconds
fun Long.formatTime(): String {
    val now = LocalTime.fromSecondOfDay(this.toInt())
    return "${now.minute.toTime()}:${now.second.toTime()}"
}

private fun Int.toTime(): String {
    return if (this.toString().length < 2) {
        "0$this"
    } else {
        "$this"
    }
}