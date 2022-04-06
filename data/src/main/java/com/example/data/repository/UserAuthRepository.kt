package com.example.data.repository

import com.example.data.api.UserService
import com.example.domain.entities.SignInEntity
import com.example.domain.entities.SignUpEntity
import com.example.domain.entities.api.RegistrateUserEntity
import com.example.domain.entities.api.RegistrateUserEntityItem
import com.example.domain.repository_interfaces.AuthRepository
import retrofit2.awaitResponse
import javax.inject.Inject

class UserAuthRepository @Inject constructor(private val userService: UserService) :
    AuthRepository {

    override suspend fun signUpUser(signUpEntity: SignUpEntity): String? {
        val userResponseModel = RegistrateUserEntity.map(signUpEntity) {
            RegistrateUserEntityItem(
                username = it.username,
                password = it.password,
                email = it.email,
                roles = listOf("User")
            )
        }
        val userResponse = userService.createUser(userResponseModel)

        return if(userResponse.code() == 200)
            null
        else
            userResponse.message()
    }

    override suspend fun signInUser(signInEntity: SignInEntity): String? {
        return null
    }
}