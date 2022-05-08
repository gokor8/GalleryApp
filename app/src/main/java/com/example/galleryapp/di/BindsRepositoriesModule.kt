package com.example.galleryapp.di

import com.example.data.parsers.RegistrationServerErrorParserImpl
import com.example.data.parsers.ServerErrorParser
import com.example.data.repository.IsUserAuthorizateRepositoryImpl
import com.example.data.repository.UserAuthorizationRepositoryImpl
import com.example.domain.repository.AuthorizationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsRepositoriesModule {

    @Binds
    abstract fun bindUserAuthorizateRepositoryImplToAuthorizationRepository(
        userAuthorizationRepositoryImpl: UserAuthorizationRepositoryImpl
    ): AuthorizationRepository
}