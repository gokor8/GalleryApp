package com.example.galleryapp.ui.models

import com.example.galleryapp.R

class RecyclerViewImageHandler : ImageHandler {
    override fun handle(id: Int?): Int = id ?: R.drawable.blicked_image
}