package com.example.data.api.models

import com.example.data.storages.KeysEntity
import com.example.domain.core.Mapper

data class ResponseTokenModel(val clientId: String, val randomId: String, val secret: String) : Mapper<KeysEntity> {

    override fun mapTo() =  KeysEntity(clientId, randomId, secret)
}