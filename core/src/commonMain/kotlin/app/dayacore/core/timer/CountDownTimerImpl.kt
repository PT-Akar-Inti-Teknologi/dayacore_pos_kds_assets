package app.dayacore.core.timer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

/**
 * class Timer is countDownTimer custom with coroutines flow.
 * this is easy for mock test
 */
class CountDownTimerImpl : CountDownTimer {

    private var job: Job? = null

    private fun init(duration: Long, countDownInterval: Long): Flow<Long> {
        return (duration - 1 downTo 0).asFlow()
            .onEach { delay(countDownInterval) }
            .onStart { emit(duration) }
            .conflate()
            .transform { remaining ->
                emit(remaining)
            }
    }

    override fun start(
        param: CountDownTimerParam,
        actionTick: (Long) -> Unit,
        launchIn: CoroutineScope
    ) {
        stop()

        if (job == null) {
            job = launchIn.launch {
                try {
                    init(
                        duration = param.duration,
                        countDownInterval = param.countDownInterval
                    ).collect {
                        actionTick.invoke(it)
                    }
                } catch (e: Exception) {
                    actionTick.invoke(0)
                }
            }
        }
    }

    override fun stop() {
        job?.cancel()
        job = null
    }
}