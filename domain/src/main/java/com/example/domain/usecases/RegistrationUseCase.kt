package com.example.domain.usecases

import com.example.domain.core.ExceptionFactory
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignUpEntity
import com.example.domain.repository.AuthorizationRepository

class RegistrationUseCase(
    private val authorizationRepository: AuthorizationRepository,
    private val exceptionFactory: ExceptionFactory
) {

    suspend fun registrationUser(signUpEntity: SignUpEntity) =
        try {
            authorizationRepository.signUpUser(signUpEntity)
        } catch (e: Exception) {
            AuthState.Exception(exceptionFactory.handle(e))
        }
}