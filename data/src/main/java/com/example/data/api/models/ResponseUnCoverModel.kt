package com.example.data.api.models

import retrofit2.Response

class ResponseUnCoverModel<M>(val response: Response<M>) {
    fun unCoverModel(): M? = response.body()
}