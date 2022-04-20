package com.example.galleryapp.di

import com.example.data.datasource.ApiTokenRegistrationDataSource
import com.example.data.datasource.SharedPreferencesDataSource
import com.example.data.managers.ApiTokenRegistrationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ManagersModule {
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

    /*@Provides
    fun provideApiTokenManager(
        apiTokenRegistrationDataSource: ApiTokenRegistrationDataSource,
        sharedPreferencesDataSource: SharedPreferencesDataSource
    ) = ApiTokenManagerBabijon(apiTokenRegistrationDataSource, sharedPreferencesDataSource)*/
}