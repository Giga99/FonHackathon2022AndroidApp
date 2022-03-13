package fon.hakaton.fonhakatonandroidapp.common

sealed class Destinations(val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route).append('/')
        params.forEach { builder.append("{$it}/") }
        builder.deleteAt(builder.length - 1)
        builder.toString()
    }

    object LoginScreen : Destinations("login")

    object HomeScreen : Destinations("home")

    object UtilitiesDetailsScreen : Destinations("utilities", "isElectricity") {
        const val IS_ELECTRICITY = "isElectricity"

        operator fun invoke(isElectricity: Boolean): String {
            return route.appendParams(IS_ELECTRICITY to isElectricity)
        }
    }

    object UtilitiesEditDetailsScreen :
        Destinations("utilities/edit", "name", "consumption", "renewable_energy") {
        const val NAME = "name"
        const val CONSUMPTION = "consumption"
        const val RENEWABLE_ENERGY = "renewable_energy"

        operator fun invoke(name: String, consumption: Long, renewableEnergy: Int?): String {
            return route.appendParams(
                NAME to name,
                CONSUMPTION to consumption,
                RENEWABLE_ENERGY to renewableEnergy,
            )
        }
    }

    object FoodDetailsScreen : Destinations("foods")

    object FoodEditDetailsScreen :
        Destinations("food/edit", "beef", "other_meat", "animal_products", "vegetables", "pastry") {
        const val BEEF = "beef"
        const val OTHER_MEAT = "other_meat"
        const val ANIMAL_PRODUCTS = "animal_products"
        const val VEGETABLES = "vegetables"
        const val PASTRY = "pastry"

        operator fun invoke(
            beef: Int,
            otherMeat: Int,
            animalProducts: Int,
            vegetables: Int,
            pastry: Int
        ): String {
            return route.appendParams(
                BEEF to beef,
                OTHER_MEAT to otherMeat,
                ANIMAL_PRODUCTS to animalProducts,
                VEGETABLES to vegetables,
                PASTRY to pastry,
            )
        }
    }
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
