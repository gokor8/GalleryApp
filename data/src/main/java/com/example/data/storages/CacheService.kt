package com.example.data.storages

interface CacheService {

    fun saveKey(key: String, value: String)

    fun saveKeys(keys: Map<String, String>)

    fun getKeys(keys: List<String>): Map<String,String>

    fun getKey(key: String): String?
}