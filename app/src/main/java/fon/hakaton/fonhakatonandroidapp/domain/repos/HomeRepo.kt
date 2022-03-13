package fon.hakaton.fonhakatonandroidapp.domain.repos

import fon.hakaton.fonhakatonandroidapp.common.Result
import fon.hakaton.fonhakatonandroidapp.data.remote.requests.UserRequest
import fon.hakaton.fonhakatonandroidapp.domain.models.CarbonOverviewModel

/**
 * @author igorstevanovic
 * Created 13.3.22. at 10:13
 */
interface HomeRepo {

    suspend fun getOverview(request: UserRequest): Result<CarbonOverviewModel>
}