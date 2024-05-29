package app.dayacore.core.utils

import androidx.compose.runtime.*
import app.dayacore.core.composables.dialog.LoaderDialog
import app.dayacore.core.composables.dialog.ProgressDialog
import app.dayacore.core.composables.dialog.SimpleDialog
import app.dayacore.core.presentation.BaseViewModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

data class StateScreenProperties(
    val alertTitle: String,
    val alertDismissTitle: String
)

@Composable
inline fun <reified ViewModel : BaseViewModel<State, Event>, State, Event> StateScreenWrapper(
    viewModel: ViewModel,
    navigator: Navigator,
    exceptionDialog: List<Int> = listOf(),
    properties: StateScreenProperties = StateScreenProperties(
        alertTitle = String.Empty,
        alertDismissTitle = String.Empty
    ),
    crossinline events: (Flow<Event>, CoroutineScope, Navigator) -> Unit,
    crossinline content: @Composable (viewModel: ViewModel, state: State) -> Unit,
    crossinline actionSimpleDialog: (viewModel: ViewModel, responseCode: Int) -> Unit = { _, _ -> }
) {
    // init
    val state: State? by viewModel.state.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()
    val progressState by viewModel.progressState.collectAsState()
    val responseState by viewModel.responseState.collectAsState()

    // event update
    events.invoke(viewModel.event, viewModel.screenModelScope, navigator)

    // build screen content with spacer top and bottom
    state?.let {
        content(viewModel, it)
    }

    // loading dialog
    if (loadingState) {
        LoaderDialog()
    }
    // progress dialog
    if (progressState.first) {
        ProgressDialog(progress = progressState.second)
    }

    // error dialog
    if (!loadingState
        && (responseState.title.isNotEmpty() || responseState.message.isNotEmpty())
        && !exceptionDialog.contains(responseState.code)
    ) {
        SimpleDialog(
            title = properties.alertTitle,
            message = responseState.message.takeIf { responseState.message.isNotEmpty() }
                ?: responseState.title,
            titleDismiss = properties.alertDismissTitle,
            onDismissed = {
                when (responseState.code) {
                    /*401 ->
                        if (navigator.lastItem.key != AuthScreen().key)
                            navigator.replaceAll(item = AuthScreen())*/

                    else -> actionSimpleDialog(viewModel, responseState.code)
                }
            }
        )
    }
}