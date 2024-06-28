package app.dayacore.domain.utils

import app.dayacore.domain.model.response.StatusResponse

sealed class ResultWrapper<out T : Any> {

    data class Success<T : Any>(
        val data: T,
    ) : ResultWrapper<T>()

    data class Error(
        val statusResponse: StatusResponse? = null,
    ) : ResultWrapper<Nothing>()
}