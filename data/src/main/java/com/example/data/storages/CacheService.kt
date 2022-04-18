package com.example.data.storages

interface CacheService {

    fun saveKeys(keys: KeysEntity)

    fun getKeys(): KeysEntity
}