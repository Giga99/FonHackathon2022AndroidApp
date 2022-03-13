package fon.hakaton.fonhakatonandroidapp.domain.repos

import fon.hakaton.fonhakatonandroidapp.common.Result
import fon.hakaton.fonhakatonandroidapp.data.remote.requests.UserRequest2
import fon.hakaton.fonhakatonandroidapp.data.remote.responses.UtilityResponse
import fon.hakaton.fonhakatonandroidapp.domain.models.UtilityModel

/**
 * @author igorstevanovic
 * Created 13.3.22. at 11:06
 */
interface UtilitiesRepo {

    suspend fun getUtility(request: UserRequest2, isElectricity: Boolean): Result<UtilityModel>

    suspend fun saveUtility(request: UtilityResponse, isElectricity: Boolean): Result<Unit>
}
