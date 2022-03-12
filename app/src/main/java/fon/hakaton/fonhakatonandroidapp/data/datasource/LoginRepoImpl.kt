package fon.hakaton.fonhakatonandroidapp.data.datasource

import fon.hakaton.fonhakatonandroidapp.common.Result
import fon.hakaton.fonhakatonandroidapp.data.remote.services.LoginApiService
import fon.hakaton.fonhakatonandroidapp.domain.repos.LoginRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

/**
 * @author igorstevanovic
 * Created 12.3.22. at 20:21
 */
class LoginRepoImpl @Inject constructor(
    private val loginApiService: LoginApiService
) : LoginRepo {

    override suspend fun login() = withContext(Dispatchers.IO) {
        try {
            loginApiService.login()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e.message ?: "")
        }
    }
}
