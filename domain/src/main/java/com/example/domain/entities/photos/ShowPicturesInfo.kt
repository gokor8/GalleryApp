package com.example.domain.entities.photos

data class ShowPicturesInfo(val page: Int, val limit: Int) {
    var loadType = Pair("","")
        set(value) {
            if (field.first == "") field = value
        }
}