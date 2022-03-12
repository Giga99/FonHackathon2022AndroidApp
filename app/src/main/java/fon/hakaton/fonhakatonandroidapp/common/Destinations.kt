package com.example.template.common

sealed class Destinations(val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route).append('/')
        params.forEach { builder.append("{$it}/") }
        builder.deleteAt(builder.length - 1)
        builder.toString()
    }

    object Destination1 : Destinations("destination1")

    object Destination2 : Destinations("destination2", "id", "name", "value") {
        const val ID = "id"
        const val NAME = "name"
        const val VALUE = "value"

        operator fun invoke(id: String, name: String, value: Long): String {
            return route.appendParams(ID to id, NAME to name, VALUE to value)
        }
    }

    object Destination3 : Destinations("destination3", "param") {
        const val PARAM = "param"

        operator fun invoke(param: Int): String {
            return route.appendParams(PARAM to param)
        }
    }

    object Destination4 : Destinations("destination4")
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
