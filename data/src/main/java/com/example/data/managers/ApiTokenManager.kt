package com.example.data.managers

import retrofit2.Response

interface ApiTokenManager {
    interface Save<I> {
        suspend fun save(response: I)
    }

    interface ReturnSave<I,R> {
        suspend fun save(response: I): R
    }
}