package com.example.galleryapp.ui.models.photo

import com.example.domain.entities.photos.PictureInfoModel
import com.example.galleryapp.core.diffutils.CommonDiffUtilsItem

data class PictureUiModel(
    override val id: Int,
    override val name: String,
    override val description: String,
    override val new: Boolean,
    override val popular: Boolean,
    override val pictureModel: PictureModel,
    override val user: String?
) : PictureInfoModel(
    id,
    name,
    description,
    new,
    popular,
    pictureModel,
    user,
), CommonDiffUtilsItem.DiffUtilModel<Int> {

    constructor(pictureInfoModel: PictureInfoModel) : this(
        pictureInfoModel.id,
        pictureInfoModel.name,
        pictureInfoModel.description,
        pictureInfoModel.new,
        pictureInfoModel.popular,
        pictureInfoModel.pictureModel,
        pictureInfoModel.user
    )

    override val comparedId: Int
        get() = id
}