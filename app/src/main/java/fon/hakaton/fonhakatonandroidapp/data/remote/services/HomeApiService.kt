package fon.hakaton.fonhakatonandroidapp.data.remote.services

import fon.hakaton.fonhakatonandroidapp.data.remote.requests.UserRequest
import fon.hakaton.fonhakatonandroidapp.data.remote.responses.CarbonOverviewResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author igorstevanovic
 * Created 13.3.22. at 10:09
 */
interface HomeApiService {

    @POST("user/overall")
    suspend fun getOverview(@Body request: UserRequest): CarbonOverviewResponse
}
