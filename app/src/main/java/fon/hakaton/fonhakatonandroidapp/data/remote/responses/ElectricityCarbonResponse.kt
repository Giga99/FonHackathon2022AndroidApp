package fon.hakaton.fonhakatonandroidapp.data.remote.responses

/**
 * @author igorstevanovic
 * Created 13.3.22. at 10:53
 */
data class ElectricityCarbonResponse(
    val electricityList: List<UtilityResponse>,
    val electricity_c: Float
)
