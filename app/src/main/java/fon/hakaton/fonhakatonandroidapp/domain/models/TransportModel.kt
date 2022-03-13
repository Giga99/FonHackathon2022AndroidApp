package fon.hakaton.fonhakatonandroidapp.domain.models

/**
 * @author igorstevanovic
 * Created 13.3.22. at 08:54
 */
data class TransportModel(
    val userId: Int,
    val carbonFootprint: Float,
    val betterThanPercent: String,
    val lastMonthConsumption: Float,
    val averageConsumption: Float,
    val walk: Int,
    val bicycle: Int,
    val car: Int,
    val bus: Int,
    val plane: Int,
)
