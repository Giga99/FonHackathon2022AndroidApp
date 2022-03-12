package fon.hakaton.fonhakatonandroidapp.domain.repos

import fon.hakaton.fonhakatonandroidapp.common.Result

/**
 * @author igorstevanovic
 * Created 12.3.22. at 20:18
 */
interface LoginRepo {

    suspend fun login(): Result<Unit>
}
