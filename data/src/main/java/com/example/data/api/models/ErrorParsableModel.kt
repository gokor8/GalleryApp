package com.example.data.api.models

interface ErrorParsableModel {

    fun parseJson(serverErrorResponse: String): String
}