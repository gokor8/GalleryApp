package com.example.data.api.entities

import com.example.data.storages.KeysEntity
import com.example.domain.core.Mapper

data class ResponseTokenEntity(val clientId: String, val randomId: String, val secret: String) : Mapper<KeysEntity> {

    override fun mapTo() =  KeysEntity(clientId, randomId, secret)
}