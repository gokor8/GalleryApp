package com.example.galleryapp.di

import com.example.data.api.ImageService
import com.example.data.api.TokenService
import com.example.data.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CloudModule {

    @Provides
    fun provideBaseUrl(): String = "http://gallery.dev.webant.ru"

    @Provides
    @Named("default_token")
    fun provideDefaultToken(): String = "960"

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
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    @Provides
    @Singleton
    fun provideApiTokenService(retrofit: Retrofit): TokenService =
        retrofit.create(TokenService::class.java)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideImageService(retrofit: Retrofit): ImageService = retrofit.create(ImageService::class.java)
}
