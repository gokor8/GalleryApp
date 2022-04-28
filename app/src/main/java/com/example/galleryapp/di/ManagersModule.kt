package com.example.galleryapp.di

import com.example.data.datasource.auth.ApiTokenRegistrationDataSource
import com.example.data.datasource.SharedPreferencesDataSource
import com.example.data.managers.ApiTokenAccessManager
import com.example.data.managers.ApiTokenRegistrationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ManagersModule {

    //Registration Manager
    @Provides
    fun provideApiTokenRegistrationManager(
        apiTokenRegistrationDataSource: ApiTokenRegistrationDataSource,
        sharedPreferencesDataSource: SharedPreferencesDataSource
    ) = ApiTokenRegistrationManager(apiTokenRegistrationDataSource, sharedPreferencesDataSource)

    @Provides
    fun provideApiTokenRegistrationManagerSave(apiTokenRegistrationManager: ApiTokenRegistrationManager) =
        apiTokenRegistrationManager.Save()

    @Provides
    fun provideApiTokenRegistrationManagerRead(apiTokenRegistrationManager: ApiTokenRegistrationManager) =
        apiTokenRegistrationManager.Read()


    //Access Tokens Manager
    @Provides
    fun provideApiTokenAccessManager(
        //apiTokenRegistrationDataSource: ApiTokenRegistrationDataSource,
        sharedPreferencesDataSource: SharedPreferencesDataSource
    ) = ApiTokenAccessManager(sharedPreferencesDataSource)

    @Provides
    fun provideApiTokenAccessManagerSave(apiTokenAccessManager: ApiTokenAccessManager) =
        apiTokenAccessManager.Save()

    @Provides
    fun provideApiTokenAccessManagerRead(apiTokenAccessManager: ApiTokenAccessManager) =
        apiTokenAccessManager.Read()
}