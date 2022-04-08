package com.example.data.repository

import com.example.data.api.ApiSignUpDataSource
import com.example.data.parsers.ValidationParser
import com.example.data.storages.CacheService
import com.example.data.storages.KeysEntity
import com.example.domain.core.ValidationTypes
import com.example.domain.entities.SignInEntity
import com.example.domain.entities.SignUpEntity
import com.example.domain.repository.AuthorizationRepository
import javax.inject.Inject

class UserAuthorizationRepository @Inject constructor(
    private val apiSignUpDataSource: ApiSignUpDataSource,
    private val cacheService: CacheService
) : AuthorizationRepository {

    override suspend fun signUpUser(signUpEntity: SignUpEntity): Map<ValidationTypes, String>? {
        val responseData = apiSignUpDataSource.signUpUser(signUpEntity)

        return if(responseData != null)
             ValidationParser.parse(responseData)
        else
            null
    }

    override suspend fun signInUser(signInEntity: SignInEntity): Map<ValidationTypes, String>? {
        return null//apiSignUpDataSource.signInUser(signInEntity)
    }
}
//        return if (responseData != null) {
//            cacheService.saveKeys(
//                KeysEntity(
//                    responseData.id.toString(),
//                    ""
//                ))
//            null
//        } else {
//            "Error"
//        }