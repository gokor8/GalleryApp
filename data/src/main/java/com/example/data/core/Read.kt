package com.example.data.core

interface Read<R> {
    suspend fun read(): R
}