package fon.hakaton.fonhakatonandroidapp.domain.models

/**
 * @author igorstevanovic
 * Created 13.3.22. at 10:14
 */
data class CarbonOverviewModel(
    val carbonOverall: Float = 0f,
    val betterThanPercentage: Long = 0,
    val electricityAmount: Float = 0f,
    val waterAmount: Float = 0f,
    val transportAmount: Float = 0f,
    val foodAmount: Float = 0f
)
