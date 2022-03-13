package fon.hakaton.fonhakatonandroidapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fon.hakaton.fonhakatonandroidapp.common.ApiUrl
import fon.hakaton.fonhakatonandroidapp.data.remote.services.HomeApiService
import fon.hakaton.fonhakatonandroidapp.data.remote.services.LoginApiService
import fon.hakaton.fonhakatonandroidapp.data.remote.services.UtilitiesApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author igorstevanovic
 * Created 11.3.22. at 22:33
 */
@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        @ApiUrl apiUrl: String,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideLoginApiService(
        retrofit: Retrofit
    ): LoginApiService = retrofit.create(LoginApiService::class.java)

    @Singleton
    @Provides
    fun provideHomeApiService(
        retrofit: Retrofit
    ): HomeApiService = retrofit.create(HomeApiService::class.java)

    @Singleton
    @Provides
    fun provideUtilitiesApiService(
        retrofit: Retrofit
    ): UtilitiesApiService = retrofit.create(UtilitiesApiService::class.java)
}
