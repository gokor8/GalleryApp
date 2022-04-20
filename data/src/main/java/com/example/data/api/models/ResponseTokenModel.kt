package com.example.data.api.models

import com.example.data.storages.RegistrationKeysModel
import com.example.domain.core.MapperTo
import com.google.gson.annotations.SerializedName

data class ResponseTokenModel(
    @SerializedName("id")
    val clientId: String,
    @SerializedName("randomId")
    val randomId: String,
    @SerializedName("secret")
    val secret: String
) : MapperTo<RegistrationKeysModel> {

    override fun mapTo() = RegistrationKeysModel(clientId, randomId, secret)

    fun hasEmpty() =
        listOf(clientId, randomId, secret).any { it.isEmpty() }
}