package com.example.domain.entities.photos

data class PictureInfoModel(
    val id: Int,
    val name: String,
    //val dateCreate: Data,
    val description: String,
    val new: Boolean,
    val popular: Boolean,
    val pictureModel: PictureModel,
    val user: String?,
) {
    data class PictureModel(
        val id: Int,
        val name: String
    )
}