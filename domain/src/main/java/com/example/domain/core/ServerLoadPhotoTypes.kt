package com.example.domain.core

enum class ServerLoadPhotoTypes(val pair: Pair<String,String>?) {
    None(null), New(Pair("new","true")), Popular(Pair("popular","true"));
}