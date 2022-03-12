package fon.hakaton.fonhakatonandroidapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @author igorstevanovic
 * Created 12.3.22. at 19:59
 */

@HiltAndroidApp
class FonHakatonApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}
