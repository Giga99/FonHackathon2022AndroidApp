package fon.hakaton.fonhakatonandroidapp.data.remote.services

import retrofit2.http.GET

/**
 * @author igorstevanovic
 * Created 12.3.22. at 19:52
 */
interface LoginApiService {

    // TODO add path
    @GET("login")
    suspend fun login(): Unit
}
