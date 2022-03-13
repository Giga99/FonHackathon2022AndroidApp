package fon.hakaton.fonhakatonandroidapp.data.remote.responses

/**
 * @author igorstevanovic
 * Created 13.3.22. at 10:10
 */
data class CarbonOverviewResponse(
    val output: Float,
    val betterThanPercentage: Long,
    val electricityAmount: Float,
    val waterAmount: Float,
    val transportAmount: Float,
    val foodAmount: Float
)
