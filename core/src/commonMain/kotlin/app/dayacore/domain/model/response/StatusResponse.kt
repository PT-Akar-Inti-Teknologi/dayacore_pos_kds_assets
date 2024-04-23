package app.dayacore.domain.model.response

data class StatusResponse(
    var isSuccess: Boolean = true,
    var code: Int = 200,
    var title: String = "",
    var message: String = "",
    var errorString: String = "",
    val exception: Exception? = null
)