package com.example.galleryapp.di

import com.example.galleryapp.ValidationHandler
import com.example.galleryapp.validators.EmailValidator
import com.example.galleryapp.validators.PasswordValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UiModule {

    /*@Provides
    fun provideValidators(): List<Validator> =
        listOf(EmailValidator(), DateValidator(), PasswordValidator())*/

    @Provides
    fun provideValidatorHandler(): ValidationHandler = //validators: List<Validator>): ValidationHandler =
        ValidationHandler(listOf(EmailValidator(), PasswordValidator()))
}