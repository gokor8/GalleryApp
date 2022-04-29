package com.example.domain.entities.photos

import com.example.domain.core.LoadPhotoTypes
import com.example.domain.core.MapperTo

class LoadPhotosModel(val page: Int, val limit: Int): MapperTo<Map<String,String>> {
    var loadType = Pair("","")

    override fun mapTo(): Map<String, String> = mapOf("page" to page.toString(), "limit" to limit.toString(), loadType)
}