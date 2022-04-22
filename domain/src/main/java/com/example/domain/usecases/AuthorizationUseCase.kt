package com.example.domain.usecases

import com.example.domain.core.ExceptionFactory
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignInEntity
import com.example.domain.repository.AuthorizationRepository

class AuthorizationUseCase(
    private val authorizationRepository: AuthorizationRepository,
    private val exceptionFactory: ExceptionFactory
) {
    suspend fun authorization(signInEntity: SignInEntity) =
        try {
            authorizationRepository.signInUser(signInEntity)
        } catch (e: Exception) {
            AuthState.Exception(exceptionFactory.handle(e))
        }
}