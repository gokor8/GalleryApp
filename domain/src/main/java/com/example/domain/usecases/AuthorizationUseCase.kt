package com.example.domain.usecases

import com.example.domain.entities.SignInEntity
import com.example.domain.repository.AuthorizationRepository

class AuthorizationUseCase(private val authorizationRepository: AuthorizationRepository) {
    suspend fun authorization(signInEntity: SignInEntity) = authorizationRepository.signInUser(signInEntity)
}