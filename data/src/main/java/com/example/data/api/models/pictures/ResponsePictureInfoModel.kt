package com.example.data.api.models.pictures

import com.example.domain.core.MapperTo
import com.example.domain.entities.photos.PictureInfoModel
import com.google.gson.annotations.SerializedName
import java.util.*

data class ResponsePictureInfoModel(
    val id: Int,
    val name: String,
    //val dateCreate: Date,
    val description: String,
    val new: Boolean,
    val popular: Boolean,
    @SerializedName("image")
    val responsePictureModel: ResponsePictureModel,
    val user: String?,
) : MapperTo<PictureInfoModel> {

    data class ResponsePictureModel(
        val id: Int,
        val name: String
    ) : MapperTo<PictureInfoModel.PictureModel> {

        override fun mapTo(): PictureInfoModel.PictureModel =
            PictureInfoModel.PictureModel(id, name)
    }

    override fun mapTo(): PictureInfoModel =
        PictureInfoModel(
            id,
            name,
            //dateCreate,
            description,
            new,
            popular,
            responsePictureModel.mapTo(),
            user
        )
}