package fon.hakaton.fonhakatonandroidapp.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fon.hakaton.fonhakatonandroidapp.common.Result.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class AppViewModel<ViewState, Intent, SideEffect>(
    viewState: ViewState,
) : ViewModel() {

    protected val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(viewState)
    val viewState = _viewState.asStateFlow()

    val intentChannel = MutableSharedFlow<Intent>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    protected val _sideEffects = MutableSharedFlow<SideEffect>(
        extraBufferCapacity = Int.MAX_VALUE,
    )
    val sideEffects = _sideEffects.asSharedFlow()

    init {
        viewModelScope.launch(context = Dispatchers.Main) {
            intentChannel.collect { processIntent(it) }
        }
    }

    protected fun setState(reducer: ViewState.() -> ViewState) {
        _viewState.update(reducer)
    }

    protected fun getState(): ViewState {
        return _viewState.value
    }

    protected open fun <T : Any?, U : Any?> (suspend () -> T).subscribe(
        flow: suspend (T) -> Flow<U>,
        subscribe: suspend (Result<U>) -> Unit,
    ): Job {
        return viewModelScope.launch(context = Dispatchers.IO) {
            runCatching {
                subscribe(Loading())
                val result = invoke()
                flow(result).catch { ex -> Timber.d("ERROR OCCURRED: $ex") }.collect {
                    subscribe(Result.Success(it))
                }
            }.onFailure { ex ->
                Timber.d("ERROR OCCURRED: $ex")
            }
        }
    }

    protected abstract suspend fun processIntent(intent: Intent)

    @Composable
    fun Collect(
        block: @Composable (ViewState, MutableSharedFlow<Intent>, SharedFlow<SideEffect>) -> Unit
    ) {
        block(_viewState.collectAsState().value, intentChannel, sideEffects)
    }
}