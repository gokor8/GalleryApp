package com.example.data.api.models.pictures

import com.google.gson.annotations.SerializedName

class ResponsePicturesPage(
    val totalItems: Int,
    val itemsPerPage: Int,
    val countOfPages: Int,
    @SerializedName("data")
    val picturesInfoResponse: Array<ResponsePictureInfoModel>
    )