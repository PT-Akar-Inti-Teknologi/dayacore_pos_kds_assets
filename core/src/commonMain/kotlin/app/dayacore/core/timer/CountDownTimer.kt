package app.dayacore.core.timer

import kotlinx.coroutines.CoroutineScope

interface CountDownTimer {
    fun start(
        param: CountDownTimerParam,
        actionTick: (Long) -> Unit, // timer, isException
        launchIn: CoroutineScope,
    )

    fun stop()
}