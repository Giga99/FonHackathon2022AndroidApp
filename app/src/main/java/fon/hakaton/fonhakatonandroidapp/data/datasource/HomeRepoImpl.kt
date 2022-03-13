package fon.hakaton.fonhakatonandroidapp.data.datasource

import fon.hakaton.fonhakatonandroidapp.common.Result
import fon.hakaton.fonhakatonandroidapp.common.Result.Error
import fon.hakaton.fonhakatonandroidapp.common.Result.Success
import fon.hakaton.fonhakatonandroidapp.data.remote.requests.UserRequest
import fon.hakaton.fonhakatonandroidapp.data.remote.services.HomeApiService
import fon.hakaton.fonhakatonandroidapp.domain.mappers.toModel
import fon.hakaton.fonhakatonandroidapp.domain.models.CarbonOverviewModel
import fon.hakaton.fonhakatonandroidapp.domain.repos.HomeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author igorstevanovic
 * Created 13.3.22. at 10:16
 */
class HomeRepoImpl @Inject constructor(
    private val homeApiService: HomeApiService
) : HomeRepo {

    override suspend fun getOverview(request: UserRequest): Result<CarbonOverviewModel> =
        withContext(Dispatchers.IO) {
            try {
                val response = homeApiService.getOverview(request)
                Success(response.toModel())
            } catch (e: Exception) {
                Error(e.message ?: "")
            }
        }
}
