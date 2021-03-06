package com.example.domain.usecases

import com.example.domain.core.handle_factories.HandleFactory
import com.example.domain.core.UiFailModel
import com.example.domain.entities.states.AuthState
import com.example.domain.entities.auth.SignInEntity
import com.example.domain.repository.AuthorizationRepository

class AuthorizationUseCase(
    private val authorizationRepository: AuthorizationRepository,
    private val mapUiFactory: HandleFactory<Exception, UiFailModel>
) {
    suspend fun authorization(signInEntity: SignInEntity) =
        try {
            authorizationRepository.signInUser(signInEntity)
        } catch (e: Exception) {
            AuthState.Exception(mapUiFactory.handle(e))
        }
}