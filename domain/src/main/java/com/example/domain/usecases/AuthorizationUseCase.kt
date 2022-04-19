package com.example.domain.usecases

import com.example.domain.entities.AuthState
import com.example.domain.entities.SignInEntity
import com.example.domain.repository.AuthorizationRepository

class AuthorizationUseCase(private val authorizationRepository: AuthorizationRepository) {
    suspend fun authorization(signInEntity: SignInEntity) =
        try {
            authorizationRepository.signInUser(signInEntity)
        } catch (e: Exception) {
            AuthState.Exception("Произошла ошибка с сетью.\r\nПовторите еще раз")
        }
    }