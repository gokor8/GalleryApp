package com.example.domain.usecases

import com.example.domain.core.handle_factories.HandleFactory
import com.example.domain.core.UiFailModel
import com.example.domain.entities.states.AuthState
import com.example.domain.entities.auth.SignUpEntity
import com.example.domain.repository.AuthorizationRepository

class RegistrationUseCase(
    private val authorizationRepository: AuthorizationRepository,
    private val mapUiFactory: HandleFactory<Exception, UiFailModel>
) {

    suspend fun registrationUser(signUpEntity: SignUpEntity) =
        try {
            authorizationRepository.signUpUser(signUpEntity)
        } catch (e: Exception) {
            AuthState.Exception(mapUiFactory.handle(e))
        }
}