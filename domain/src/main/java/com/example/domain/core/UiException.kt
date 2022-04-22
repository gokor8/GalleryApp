package com.example.domain.core

interface UiException {
    fun getMessageResId(): Int

    fun getMessage(): String
}