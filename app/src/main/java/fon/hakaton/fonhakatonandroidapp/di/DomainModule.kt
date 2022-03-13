package fon.hakaton.fonhakatonandroidapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fon.hakaton.fonhakatonandroidapp.data.datasource.HomeRepoImpl
import fon.hakaton.fonhakatonandroidapp.data.datasource.LoginRepoImpl
import fon.hakaton.fonhakatonandroidapp.data.datasource.UtilitiesRepoImpl
import fon.hakaton.fonhakatonandroidapp.domain.repos.HomeRepo
import fon.hakaton.fonhakatonandroidapp.domain.repos.LoginRepo
import fon.hakaton.fonhakatonandroidapp.domain.repos.UtilitiesRepo

/**
 * @author igorstevanovic
 * Created 11.3.22. at 23:11
 */
@InstallIn(SingletonComponent::class)
@Module
interface DomainModule {

    @Binds
    fun bindLoginRepo(loginRepoImpl: LoginRepoImpl): LoginRepo

    @Binds
    fun bindHomeRepo(homeRepoImpl: HomeRepoImpl): HomeRepo

    @Binds
    fun bindUtilitiesRepo(utilitiesRepoImpl: UtilitiesRepoImpl): UtilitiesRepo
}
