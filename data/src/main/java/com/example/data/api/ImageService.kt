package com.example.data.api

import retrofit2.http.GET

interface ImageService {

    @GET("")
    suspend fun getImagePage()
}