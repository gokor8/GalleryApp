package com.example.data.storages

import com.example.domain.core.MapperFrom

class RegistrationKeysModel(clientId: String, randomId: String, secret: String) : MapperFrom<Map<String, String>, RegistrationKeysModel> {

    constructor() : this("", "", "")

    var clientId: String = clientId
        private set
    var randomId: String = randomId
        private set
    var secret: String = secret
        private set

    companion object {
        const val CLIENT_ID = "client_id"
        const val RANDOM_ID = "random_id"
        const val SECRET = "secret"
    }

    override fun mapTo(inputModel: Map<String, String>): RegistrationKeysModel {
        for (pairKeys in inputModel) {
            when (pairKeys.key) {
                CLIENT_ID -> clientId = pairKeys.value
                RANDOM_ID -> randomId = pairKeys.value
                SECRET -> secret = pairKeys.value
            }
        }

        return this
    }
}