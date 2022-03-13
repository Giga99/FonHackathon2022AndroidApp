package fon.hakaton.fonhakatonandroidapp.data.remote.services

import fon.hakaton.fonhakatonandroidapp.data.remote.requests.UserRequest
import fon.hakaton.fonhakatonandroidapp.data.remote.responses.UtilityResponse

/**
 * @author igorstevanovic
 * Created 13.3.22. at 10:52
 */
interface UtilitiesApiService {

    fun getElectricity(request: UserRequest): List<UtilityResponse>

    fun getWater(request: UserRequest): List<UtilityResponse>
}