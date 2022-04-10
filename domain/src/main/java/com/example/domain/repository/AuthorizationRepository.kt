package com.example.domain.repository

import com.example.domain.core.ValidationTypes
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignInEntity
import com.example.domain.entities.SignUpEntity

interface AuthorizationRepository {

    suspend fun signUpUser(signUpEntity: SignUpEntity) : AuthState

    suspend fun signInUser(signInEntity: SignInEntity) : AuthState
}