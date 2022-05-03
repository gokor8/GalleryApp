package com.example.domain.entities.photos

open class PictureInfoDomainEntity(
    open val id: Int,
    open val name: String,
    //val dateCreate: Data,
    open val description: String,
    open val new: Boolean,
    open val popular: Boolean,
    open val pictureDomainEntity: PictureDomainEntity,
    open val user: String?,
) {
    data class PictureDomainEntity(
        val id: Int,
        val name: String
    )
}