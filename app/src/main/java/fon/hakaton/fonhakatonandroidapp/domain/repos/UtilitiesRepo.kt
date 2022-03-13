package fon.hakaton.fonhakatonandroidapp.domain.repos

import fon.hakaton.fonhakatonandroidapp.data.remote.requests.UserRequest
import fon.hakaton.fonhakatonandroidapp.domain.models.UtilityModel

/**
 * @author igorstevanovic
 * Created 13.3.22. at 11:06
 */
interface UtilitiesRepo {

    suspend fun getUtility(request: UserRequest, isElectricity: Boolean): Result<List<UtilityModel>>
}
