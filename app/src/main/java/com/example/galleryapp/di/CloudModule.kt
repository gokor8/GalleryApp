package com.example.galleryapp.di

import com.example.data.api.services.ImageService
import com.example.data.api.services.TokenService
import com.example.data.api.services.UserService
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

    private const val mainBaseUrl = "http://gallery.dev.webant.ru"

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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(mainBaseUrl)
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
