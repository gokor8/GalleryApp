package com.example.domain.usecases

import com.example.domain.entities.SignUpEntity
import com.example.domain.repository.AuthorizationRepository

class RegistrationUseCase(private val authorizationRepository: AuthorizationRepository) {

    suspend fun registrationUser(signUpEntity: SignUpEntity) =
        authorizationRepository.signUpUser(signUpEntity)
}