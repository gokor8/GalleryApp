package com.example.domain.usecases

import com.example.domain.entities.AuthState
import com.example.domain.entities.SignUpEntity
import com.example.domain.repository.AuthorizationRepository

class RegistrationUseCase(private val authorizationRepository: AuthorizationRepository) {

    suspend fun registrationUser(signUpEntity: SignUpEntity) =
        try {
            authorizationRepository.signUpUser(signUpEntity)
        } catch (e: Exception) {
            AuthState.Exception("Произошла ошибка с сетью.\r\nПовторите еще раз")
        }
}