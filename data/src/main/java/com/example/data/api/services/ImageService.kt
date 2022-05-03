package com.example.data.api.services

import com.example.data.api.models.pictures.ResponsePicturesPage
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ImageService {

    @GET("/api/photos")
    suspend fun getImagePage(
        @QueryMap queryMap: Map<String,String>
    ): ResponsePicturesPage
}