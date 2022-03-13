package fon.hakaton.fonhakatonandroidapp.domain.models

/**
 * @author igorstevanovic
 * Created 13.3.22. at 09:50
 */
data class UserModel(
    val name: String,
    val username: String,
    val id: Int,
    val apartmentSize: Int,
    val residents: Int
)
