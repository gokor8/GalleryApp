package com.example.data.storages

interface Cache {

    interface Read<I,R> {
        fun readByKey(key: I): R
    }
    interface Save<T> {
        fun<T> save(saveData: T)
    }

    interface Mutable<I,R> : Read<I,R>, Save<I>

    /*fun saveKey(key: String, value: String)

    fun saveKeys(keys: Map<String, String>)

    fun getKeys(keys: List<String>): Map<String,String?>

    fun getKey(key: String): String?*/
}