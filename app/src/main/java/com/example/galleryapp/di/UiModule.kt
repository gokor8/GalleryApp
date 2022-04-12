package com.example.galleryapp.di

import com.example.galleryapp.ValidationHandler
import com.example.galleryapp.validators.EmailValidator
import com.example.galleryapp.validators.PasswordValidator
import com.example.galleryapp.validators.Validator
import com.example.galleryapp.validators.multi_data_validators.MultiDataValidator
import com.example.galleryapp.validators.multi_data_validators.PasswordsMultiDataValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UiModule {

    @Provides
    fun provideMultiDataValidatorsList(): ArrayList<MultiDataValidator> =
        arrayListOf(PasswordsMultiDataValidator())

    @Provides
    fun provideValidatorsList(): ArrayList<Validator> =
        arrayListOf(EmailValidator(), PasswordValidator())

    @Provides
    fun provideValidatorHandler(
        validators: ArrayList<Validator>,
        multiDataValidators: ArrayList<MultiDataValidator>
    ): ValidationHandler =
        ValidationHandler(validators, multiDataValidators)
}