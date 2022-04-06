package com.example.domain.repository_interfaces

import com.example.domain.entities.SignInEntity
import com.example.domain.entities.SignUpEntity

interface AuthRepository {

    suspend fun signUpUser(signUpEntity: SignUpEntity): String?

    suspend fun signInUser(signInEntity: SignInEntity): String?
}