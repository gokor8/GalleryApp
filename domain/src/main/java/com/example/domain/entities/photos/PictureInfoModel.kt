package com.example.domain.entities.photos

open class PictureInfoModel(
    open val id: Int,
    open val name: String,
    //val dateCreate: Data,
    open val description: String,
    open val new: Boolean,
    open val popular: Boolean,
    open val pictureModel: PictureModel,
    open val user: String?,
) {
    data class PictureModel(
        val id: Int,
        val name: String
    )
}