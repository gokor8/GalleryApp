package com.example.data.api.models.pictures

import com.example.domain.core.MapperFrom
import com.example.domain.core.MapperTo
import com.example.domain.entities.photos.ShowPicturesInfo

class RequestPictureModel(val page: Int?, val limit: Int?, val loadType: Pair<String, String>) :
    MapperFrom<ShowPicturesInfo, RequestPictureModel>,
    MapperTo<Map<String, String>> {

    constructor() : this(null, null, Pair("", ""))

    override fun mapTo(): Map<String, String> =
        mapOf("page" to page.toString(), "limit" to limit.toString(), loadType)

    override fun mapTo(inputModel: ShowPicturesInfo): RequestPictureModel =
        RequestPictureModel(inputModel.page, inputModel.limit, inputModel.loadType)
}