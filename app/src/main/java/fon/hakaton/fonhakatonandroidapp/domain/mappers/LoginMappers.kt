package fon.hakaton.fonhakatonandroidapp.domain.mappers

import fon.hakaton.fonhakatonandroidapp.data.remote.requests.LoginRequest
import fon.hakaton.fonhakatonandroidapp.data.remote.responses.UserResponse
import fon.hakaton.fonhakatonandroidapp.domain.models.LoginModel
import fon.hakaton.fonhakatonandroidapp.domain.models.UserModel

/**
 * @author igorstevanovic
 * Created 12.3.22. at 20:20
 */

fun LoginModel.toRequest() = LoginRequest(username = username, password = password)

fun UserResponse.toModel() = UserModel(
    username = username,
    name = name,
    id = id,
    apartmentSize = apartment_size,
    residents = residents,
)
