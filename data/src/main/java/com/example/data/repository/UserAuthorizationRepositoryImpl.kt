package com.example.data.repository

import com.example.data.datasource.CloudAuthDataSource
import com.example.data.datasource.CloudTokenDataSource
import com.example.data.parsers.ValidationParser
import com.example.data.storages.CacheService
import com.example.domain.core.ValidationTypes
import com.example.domain.entities.SignInEntity
import com.example.domain.entities.SignUpEntity
import com.example.domain.repository.AuthorizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserAuthorizationRepositoryImpl @Inject constructor(
    private val cloudAuthDataSource: CloudAuthDataSource,
    private val cloudTokenDataSource: CloudTokenDataSource,
    private val cacheService: CacheService
) : AuthorizationRepository {

    override suspend fun signUpUser(signUpEntity: SignUpEntity): Map<ValidationTypes, String>? {
        return withContext(Dispatchers.IO) {
            val responseData = cloudAuthDataSource.signUpUser(signUpEntity)

            var tokens = cacheService.getKeys()
            if(tokens.clientId == null)
            {
                tokens = cloudTokenDataSource.getNewToken().mapTo()
                cacheService.saveKeys(tokens)
            } else {
                val apiTokens = cloudTokenDataSource.getAvailableToken(tokens.clientId!!).mapTo()
                if(apiTokens != tokens)
                    cacheService.saveKeys(apiTokens)
                tokens = apiTokens
            }

            return@withContext if (responseData != null)
                ValidationParser.parse(responseData)
            else
                null
        }
    }

    override suspend fun signInUser(signInEntity: SignInEntity): Map<ValidationTypes, String>? {
        return null//authCloudDataSource.signInUser(signInEntity)
    }
}
