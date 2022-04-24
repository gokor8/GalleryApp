package com.example.data.storages

interface CacheSharedPreferences : Cache {

    interface Read : Cache.Read<String, String> {
        fun readKeys(keys: List<String>): Map<String, String?>
    }

    interface Save : Cache.Save<String> {
        fun<V> saveKey(key: String, value: V)
        fun<V> saveKeys(keys: Map<String, V>)
    }

    interface Mutable : Read, Save
}