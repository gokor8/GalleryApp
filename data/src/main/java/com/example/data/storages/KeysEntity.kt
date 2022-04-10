package com.example.data.storages

data class KeysEntity(val clientId: String?,
                      val randomId: String,
                      val secret: String
                      ) {

    companion object{
        const val CLIENT_ID = "client_id"
        const val RANDOM_ID = "random_id"
        const val SECRET = "secret"
    }
}