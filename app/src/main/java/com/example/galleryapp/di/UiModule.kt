package com.example.galleryapp.di

import com.example.galleryapp.validators.validators_impl.EmailSingleValidator
import com.example.galleryapp.validators.validators_impl.PasswordSingleValidator
import com.example.galleryapp.validators.SingleValidator
import com.example.galleryapp.validators.validators_impl.PasswordsMultiDataValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(SingletonComponent::class)
//class UiModule {
//
//    @Provides
//    fun provideMultiDataValidatorsList(): ArrayList<MultiDataValidator> =
//        arrayListOf(PasswordsMultiDataValidator())
//
//    @Provides
//    fun provideValidatorsList(): ArrayList<SingleValidator> =
//        arrayListOf(EmailSingleValidator(), PasswordSingleValidator())
//
//    @Provides
//    fun provideValidatorHandler(
//        singleValidators: ArrayList<SingleValidator>,
//        multiDataValidators: ArrayList<MultiDataValidator>
//    ): ValidationHandler =
//        ValidationHandler(singleValidators, multiDataValidators)
//}