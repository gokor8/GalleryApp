package com.example.data.managers

import retrofit2.Response

interface ApiTokenManager {

    interface Read<R> {
        suspend fun read() : R
    }
    interface Save<I> {
        suspend fun save(response: I)
    }
}