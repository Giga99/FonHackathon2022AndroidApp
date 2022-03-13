package fon.hakaton.fonhakatonandroidapp.domain.mappers

import fon.hakaton.fonhakatonandroidapp.data.remote.responses.CarbonOverviewResponse
import fon.hakaton.fonhakatonandroidapp.domain.models.CarbonOverviewModel

/**
 * @author igorstevanovic
 * Created 13.3.22. at 10:15
 */

fun CarbonOverviewResponse.toModel() = CarbonOverviewModel(
    carbonOverall = output,
    betterThanPercentage = betterThanPercentage,
    electricityAmount = electricityAmount,
    waterAmount = waterAmount,
    transportAmount = transportAmount,
    foodAmount = foodAmount,
)
