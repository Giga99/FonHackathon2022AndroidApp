package fon.hakaton.fonhakatonandroidapp.domain.mappers

import fon.hakaton.fonhakatonandroidapp.data.remote.responses.ElectricityCarbonResponse
import fon.hakaton.fonhakatonandroidapp.data.remote.responses.UtilityResponse
import fon.hakaton.fonhakatonandroidapp.data.remote.responses.WaterCarbonResponse
import fon.hakaton.fonhakatonandroidapp.domain.models.UtilityModel

/**
 * @author igorstevanovic
 * Created 13.3.22. at 11:28
 */
fun ElectricityCarbonResponse.toModel() =
    UtilityModel(
        name = "Electricity",
        carbonFootprint = electricity_c,
        lastMonthConsumption = electricityList.sortedBy { it.month }.reversed().first().amount,
        averageConsumption = electricityList.getAverage(),
        lastFiveMonths = electricityList.sortedBy { it.month }.reversed().takeLast(5)
            .mapIndexed { index, item ->
                when (index) {
                    0 -> Pair("Feb", item.amount)
                    1 -> Pair("Jan", item.amount)
                    2 -> Pair("Dec", item.amount)
                    3 -> Pair("Nov", item.amount)
                    else -> Pair("Oct", item.amount)
                }
            },
        isElectricity = true
    )

fun WaterCarbonResponse.toModel() =
    UtilityModel(
        name = "Water",
        carbonFootprint = water_c,
        lastMonthConsumption = waterList.sortedBy { it.month }.reversed().first().amount,
        averageConsumption = waterList.getAverage(),
        lastFiveMonths = waterList.sortedBy { it.month }.reversed().takeLast(5)
            .mapIndexed { index, item ->
                when (index) {
                    0 -> Pair("Feb", item.amount)
                    1 -> Pair("Jan", item.amount)
                    2 -> Pair("Dec", item.amount)
                    3 -> Pair("Nov", item.amount)
                    else -> Pair("Oct", item.amount)
                }
            },
        isElectricity = false
    )

fun List<UtilityResponse>.getAverage(): Float {
    var avg: Long = 0
    this.forEach { avg += it.amount }
    return avg.toFloat() / this.size.toFloat()
}
