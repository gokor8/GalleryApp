package com.example.data.storages

interface Cache {

    interface Read<I,R> {
        fun readByKey(key: I): R
    }
    interface Save<T> {
        fun<T> save(saveData: T)
    }

    interface Mutable<I,R> : Read<I,R>, Save<I>
}