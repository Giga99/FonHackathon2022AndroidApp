package fon.hakaton.fonhakatonandroidapp.domain.mappers

import fon.hakaton.fonhakatonandroidapp.data.remote.requests.LoginRequest
import fon.hakaton.fonhakatonandroidapp.domain.models.LoginModel

/**
 * @author igorstevanovic
 * Created 12.3.22. at 20:20
 */

fun LoginModel.toRequest() = LoginRequest(username = email, password = password)
