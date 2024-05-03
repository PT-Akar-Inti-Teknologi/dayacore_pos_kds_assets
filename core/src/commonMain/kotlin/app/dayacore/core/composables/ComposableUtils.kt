package app.dayacore.core.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

@Composable
fun <T> rememberWithDerived(calculation: () -> T, vararg key: Any): State<T> {
    return remember(keys = key) {
        derivedStateOf(calculation)
    }
}