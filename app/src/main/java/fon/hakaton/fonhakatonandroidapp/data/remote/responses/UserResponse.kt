package fon.hakaton.fonhakatonandroidapp.data.remote.responses

/**
 * @author igorstevanovic
 * Created 13.3.22. at 09:49
 */
data class UserResponse(
    val name: String,
    val username: String,
    val id: Int,
    val apartment_size: Int,
    val residents: Int,
)
