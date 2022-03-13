package fon.hakaton.fonhakatonandroidapp.data.remote.responses

import fon.hakaton.fonhakatonandroidapp.data.remote.requests.CarbonUser

/**
 * @author igorstevanovic
 * Created 13.3.22. at 10:53
 */
data class UtilityResponse(
    val carbonUser: CarbonUser,
    val amount: Long,
    val carbon_footprint: Long? = null,
    val residents: Long,
    val month: Long? = null,
    val apartment_size: Long
)
