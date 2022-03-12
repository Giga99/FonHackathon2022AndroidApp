package fon.hakaton.fonhakatonandroidapp.common

sealed class Destinations(val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route).append('/')
        params.forEach { builder.append("{$it}/") }
        builder.deleteAt(builder.length - 1)
        builder.toString()
    }

    object Login : Destinations("login")
}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this).append('/')

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("$arg/")
        }
    }
    builder.deleteAt(builder.length - 1)

    return builder.toString()
}
