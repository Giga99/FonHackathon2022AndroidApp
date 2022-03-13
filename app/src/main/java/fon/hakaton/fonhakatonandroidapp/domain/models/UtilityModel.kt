package fon.hakaton.fonhakatonandroidapp.domain.models

/**
 * @author igorstevanovic
 * Created 13.3.22. at 04:01
 */
data class UtilityModel(
    val name: String = "Electricity",
    val betterThanPercent: String = "40%",
    val carbonFootprint: Float = 42.5f,
    val lastMonthConsumption: Long = 891,
    val averageConsumption: Long = 936,
    val lastFiveMonths: List<Pair<String, Int>> = listOf(
        "Nov" to 930,
        "Dec" to 960,
        "Jan" to 990,
        "Feb" to 930,
        "Mar" to 891
    ),
    val tip: TipModel = TipModel(
        "Standby power draw",
        "Despite being “switched off” almost all electrical devices continue operate in a standby mode and continue using electricity even when they’re not in active use. This standby power draw accounts for about 10% of an average household's annual electricity use. Unplug your appliances or turn them off at the socket to reduce your monthly carbon footprint by x%."
    ),
    val isElectricity: Boolean = true,
    val renewableEnergyPercent: Int? = 34,
)
