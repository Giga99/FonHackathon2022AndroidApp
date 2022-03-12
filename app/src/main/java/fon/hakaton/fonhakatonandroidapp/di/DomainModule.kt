package fon.hakaton.fonhakatonandroidapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fon.hakaton.fonhakatonandroidapp.data.datasource.LoginRepoImpl
import fon.hakaton.fonhakatonandroidapp.domain.repos.LoginRepo

/**
 * @author igorstevanovic
 * Created 11.3.22. at 23:11
 */
@InstallIn(SingletonComponent::class)
@Module
interface DomainModule {

    @Binds
    fun bindLoginRepo(loginRepoImpl: LoginRepoImpl): LoginRepo
}
