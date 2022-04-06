package com.example.galleryapp.di

import com.example.data.api.UserService
import com.example.data.repository.UserAuthRepository
import com.example.domain.repository_interfaces.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideBaseUrl(): String = "http://gallery.dev.webant.ru/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String) = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideRegistrationRemoteData(userService: UserService): AuthRepository =
        UserAuthRepository(userService)
}
