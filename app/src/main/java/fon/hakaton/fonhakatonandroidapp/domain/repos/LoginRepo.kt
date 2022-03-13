package fon.hakaton.fonhakatonandroidapp.domain.repos

import fon.hakaton.fonhakatonandroidapp.common.Result
import fon.hakaton.fonhakatonandroidapp.domain.models.LoginModel

/**
 * @author igorstevanovic
 * Created 12.3.22. at 20:18
 */
interface LoginRepo {

    suspend fun login(loginModel: LoginModel): Result<Unit>
}
