package com.example.data.api.models.pictures

import com.example.domain.core.MapperTo
import com.example.domain.entities.photos.PictureInfoDomainEntity
import com.google.gson.annotations.SerializedName

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
) : MapperTo<PictureInfoDomainEntity> {

    data class ResponsePictureModel(
        val id: Int,
        val name: String
    ) : MapperTo<PictureInfoDomainEntity.PictureDomainEntity> {

        override fun mapTo(): PictureInfoDomainEntity.PictureDomainEntity =
            PictureInfoDomainEntity.PictureDomainEntity(id, name)
    }

    override fun mapTo(): PictureInfoDomainEntity =
        PictureInfoDomainEntity(
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