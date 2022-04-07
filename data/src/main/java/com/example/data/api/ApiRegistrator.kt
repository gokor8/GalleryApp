package com.example.data.api

import com.example.domain.entities.SignUpEntity
import com.example.data.api.entities.RegUserRequestEntity
import com.example.data.api.entities.RegUserResponseEntity
import javax.inject.Inject

class ApiRegistrator @Inject constructor(private val userService: UserService) {

    suspend fun signUpUser(signUpEntity: SignUpEntity): RegUserResponseEntity? {
        val userResponseModel =
            RegUserRequestEntity(
                username = signUpEntity.username,
                password = signUpEntity.password,
                email = signUpEntity.email,
                birthday = signUpEntity.birthday,
                roles = listOf("User")
            )
        val userResponse = userService.createUser(userResponseModel)

        return if (userResponse.code() == 201)
            userResponse.body()
        else
            null//userResponse.message()
    }
}