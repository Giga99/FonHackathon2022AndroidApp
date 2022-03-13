package fon.hakaton.fonhakatonandroidapp.domain.models

/**
 * @author igorstevanovic
 * Created 13.3.22. at 07:01
 */
data class FoodModel(
    val userId: Int,
    val animalProducts: Int,
    val pastry: Int,
    val vegetables: Int,
    val beef: Int,
    val otherMeat: Int,
    val carbonFootprint: Float,
    val betterThanPercent: String,
    val lastMonthConsumption: Float,
    val averageConsumption: Float,
    val tip: TipModel
)
