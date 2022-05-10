package com.example.galleryapp.ui.models.photo

import com.example.domain.core.MapperFrom
import com.example.domain.entities.photos.PictureInfoDomainEntity
import com.example.galleryapp.core.diffutils.CommonDiffUtilsItem
import com.example.galleryapp.core.diffutils.DiffUtilModel

data class PictureInfoUiModel(
    val id: Int,
    val name: String?,
    val description: String?,
    val new: Boolean,
    val popular: Boolean,
    val pictureUiModel: PictureUiModel,
    val user: String?
) : DiffUtilModel<Int>,
    MapperFrom<PictureInfoDomainEntity, PictureInfoUiModel> {

    constructor() : this(
        0,
        "",
        "",
        false,
        false,
        PictureUiModel(0, ""),
        null
    )

    override val comparedId: Int
        get() = id

    data class PictureUiModel(
        val id: Int,
        val urlName: String
    ) : MapperFrom<PictureInfoDomainEntity.PictureDomainEntity, PictureUiModel> {

        override fun mapTo(inputModel: PictureInfoDomainEntity.PictureDomainEntity): PictureUiModel =
            PictureUiModel(inputModel.id, inputModel.name)
    }

    override fun mapTo(inputModel: PictureInfoDomainEntity): PictureInfoUiModel =
        PictureInfoUiModel(
            inputModel.id,
            inputModel.name,
            inputModel.description,
            inputModel.new,
            inputModel.popular,
            this.pictureUiModel.mapTo(inputModel.pictureDomainEntity),
            inputModel.user
        )
}