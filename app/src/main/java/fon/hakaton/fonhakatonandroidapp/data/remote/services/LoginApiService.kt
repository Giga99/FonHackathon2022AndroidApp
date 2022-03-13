package fon.hakaton.fonhakatonandroidapp.data.remote.services

import fon.hakaton.fonhakatonandroidapp.data.remote.requests.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author igorstevanovic
 * Created 12.3.22. at 19:52
 */
interface LoginApiService {

    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): Unit
}
