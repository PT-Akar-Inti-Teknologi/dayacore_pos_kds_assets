package app.dayacore.data.local.model.kds

import app.dayacore.core.utils.Empty
import kotlinx.serialization.Serializable

@Serializable
data class KdsSettingsData(
    var urlToLoad: String = String.Empty,
    var viewMode: String = String.Empty,
    var visitPurposeId: List<String> = emptyList(),
    var timeFirstWarning: Long = 5,
    var timeSecondWarning: Long = 10,
    var stationsId: List<String> = emptyList(),
    var printingStationId: List<String> = emptyList(),
)
