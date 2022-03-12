package fon.hakaton.fonhakatonandroidapp.common

import android.content.res.Resources
import androidx.annotation.ArrayRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppResources @Inject constructor(
    private val resources: Resources
) {
    fun getString(@StringRes id: Int): String {
        return resources.getString(id)
    }

    fun getString(@StringRes id: Int, vararg formatArgs: Any): String {
        return resources.getString(id, *formatArgs)
    }

    fun getStringArray(@ArrayRes id: Int): Array<String> {
        return resources.getStringArray(id)
    }

    fun getQuantityString(@PluralsRes id: Int, quantity: Int): String {
        return resources.getQuantityString(id, quantity)
    }

    fun getQuantityString(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any): String {
        return resources.getQuantityString(id, quantity, *formatArgs)
    }
}
