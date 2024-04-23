package app.dayacore.domain.model.response

data class Pagination(
    val totalData: Int,
    val totalPage: Int,
    val currentPage: Int,
)
