package com.example.data.storages

data class KeysEntity(val randomId: String, val secret: String) {

    companion object{
        const val RANDOM_ID = "random_id"
        const val SECRET = "secret"
    }
}