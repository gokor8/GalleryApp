package com.example.domain.repository

import com.example.domain.entities.states.AuthState
import com.example.domain.entities.auth.SignInEntity
import com.example.domain.entities.auth.SignUpEntity

interface AuthorizationRepository {

    suspend fun signUpUser(signUpEntity: SignUpEntity) : AuthState

    suspend fun signInUser(signInEntity: SignInEntity) : AuthState
}