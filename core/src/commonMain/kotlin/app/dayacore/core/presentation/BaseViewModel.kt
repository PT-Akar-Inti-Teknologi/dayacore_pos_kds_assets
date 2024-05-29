package app.dayacore.core.presentation

import app.dayacore.domain.model.response.StatusResponse
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

/**
 * Base for all the ViewModels
 */
abstract class BaseViewModel<STATE : UiState, EVENT>(
    initialState: STATE,
) : ScreenModel, KoinComponent {

    // UI state
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    val currentState: STATE get() = state.value
    protected fun setState(update: (old: STATE) -> STATE): STATE = _state.updateAndGet(update)

    // UI event
    private val eventChannel = Channel<EVENT>()
    val event = eventChannel.receiveAsFlow()

    fun sendEvent(event: EVENT) = screenModelScope.launch {
        eventChannel.send(event)
    }

    // loading state
    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    fun setLoading(isLoading: Boolean = true) {
        _loadingState.value = isLoading
    }

    // progress state
    private val _progressState = MutableStateFlow(Pair(first = false, second = 0f))
    val progressState = _progressState.asStateFlow()

    fun setProgress(isLoading: Boolean = true, progress: Float = 1f) {
        _progressState.value = Pair(first = isLoading, second = progress)
    }

    // error state
    private val _responseState = MutableStateFlow(StatusResponse())
    val responseState = _responseState.asStateFlow()

    fun setStatusResponse(statusResponse: StatusResponse = StatusResponse()) {
        _responseState.value = statusResponse
    }

}
