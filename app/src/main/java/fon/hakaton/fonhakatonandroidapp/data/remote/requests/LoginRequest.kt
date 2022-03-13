package fon.hakaton.fonhakatonandroidapp.data.remote.requests

/**
 * @author igorstevanovic
 * Created 12.3.22. at 20:20
 */
data class LoginRequest(
    val username: String,
    val password: String,
    val name: String = "Igor",
    val apartment_size: Long = 1,
    val residents: Long = 4,
)
