package fon.hakaton.fonhakatonandroidapp.data.datasource

import fon.hakaton.fonhakatonandroidapp.common.Result
import fon.hakaton.fonhakatonandroidapp.data.remote.requests.UserRequest2
import fon.hakaton.fonhakatonandroidapp.data.remote.responses.UtilityResponse
import fon.hakaton.fonhakatonandroidapp.data.remote.services.UtilitiesApiService
import fon.hakaton.fonhakatonandroidapp.domain.mappers.toModel
import fon.hakaton.fonhakatonandroidapp.domain.models.UtilityModel
import fon.hakaton.fonhakatonandroidapp.domain.repos.UtilitiesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author igorstevanovic
 * Created 13.3.22. at 11:12
 */
class UtilitiesRepoImpl @Inject constructor(
    private val utilitiesApiService: UtilitiesApiService
) : UtilitiesRepo {

    override suspend fun getUtility(
        request: UserRequest2,
        isElectricity: Boolean
    ): Result<UtilityModel> = withContext(Dispatchers.IO) {
        try {
            if (isElectricity) {
                val response = utilitiesApiService.getElectricity(request)
                Result.Success(response.toModel())
            } else {
                val response = utilitiesApiService.getWater(request)
                Result.Success(response.toModel())
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "")
        }
    }

    override suspend fun saveUtility(
        request: UtilityResponse,
        isElectricity: Boolean
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            if (isElectricity) {
                utilitiesApiService.saveElectricity(request)
            } else {
                utilitiesApiService.saveWater(request)
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e.message ?: "")
        }
    }
}
