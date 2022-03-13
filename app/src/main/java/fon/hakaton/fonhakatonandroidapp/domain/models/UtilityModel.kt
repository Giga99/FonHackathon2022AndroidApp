package fon.hakaton.fonhakatonandroidapp.domain.models

import fon.hakaton.fonhakatonandroidapp.ui.bar.BarChartData
import fon.hakaton.fonhakatonandroidapp.ui.theme.ButtonLightGreen

/**
 * @author igorstevanovic
 * Created 13.3.22. at 04:01
 */
data class UtilityModel(
    val name: String = "Electricity",
//    val betterThanPercent: String = "40%",
    val carbonFootprint: Float = 42f,
    val lastMonthConsumption: Long = 891,
    val averageConsumption: Float = 936f,
    val lastFiveMonths: List<Pair<String, Long>> = listOf(
        "Nov" to 930,
        "Dec" to 960,
        "Jan" to 990,
        "Feb" to 930,
        "Mar" to 891
    ),
    val isElectricity: Boolean = true,
    val renewableEnergyPercent: Int? = 34,
)

fun Pair<String, Long>.toBarData() =
    BarChartData.Bar(
        label = first,
        value = second.toFloat(),
        color = ButtonLightGreen
    )
