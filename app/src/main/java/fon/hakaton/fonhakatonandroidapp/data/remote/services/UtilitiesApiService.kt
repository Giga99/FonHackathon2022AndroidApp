package fon.hakaton.fonhakatonandroidapp.data.remote.services

import fon.hakaton.fonhakatonandroidapp.data.remote.requests.UserRequest2
import fon.hakaton.fonhakatonandroidapp.data.remote.responses.ElectricityCarbonResponse
import fon.hakaton.fonhakatonandroidapp.data.remote.responses.UtilityResponse
import fon.hakaton.fonhakatonandroidapp.data.remote.responses.WaterCarbonResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author igorstevanovic
 * Created 13.3.22. at 10:52
 */
interface UtilitiesApiService {

    @POST("electricity/details")
    suspend fun getElectricity(@Body request: UserRequest2): ElectricityCarbonResponse

    @POST("electricity/save")
    suspend fun saveElectricity(@Body request: UtilityResponse)

    @POST("water/details")
    suspend fun getWater(@Body request: UserRequest2): WaterCarbonResponse

    @POST("water/save")
    suspend fun saveWater(@Body request: UtilityResponse)
}
