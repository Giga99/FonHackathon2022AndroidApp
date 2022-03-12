package fon.hakaton.fonhakatonandroidapp.di

import android.app.Application
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fon.hakaton.fonhakatonandroidapp.common.ApiUrl

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideResources(application: Application): Resources = application.resources

    @Provides
    @ApiUrl
    fun provideApiUrl(): String = "https://catfact.ninja/" // TODO API URL HERE
}
