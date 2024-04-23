package app.dayacore.core.timer

data class CountDownTimerParam(
    val duration: Long,
    val countDownInterval: Long = 1000
)