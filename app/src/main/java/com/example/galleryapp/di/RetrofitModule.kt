package com.example.galleryapp.di

import com.example.data.api.ApiRegistrator
import com.example.data.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideHttpInterceptor() = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient) = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun provideApiAuth(userService: UserService) = ApiRegistrator(userService)
}
