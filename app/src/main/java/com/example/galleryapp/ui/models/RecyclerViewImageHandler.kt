package com.example.galleryapp.ui.models

import com.example.domain.core.handle_factories.HandleFactory
import com.example.galleryapp.R

class RecyclerViewImageHandler : HandleFactory<Int?, Int> {
    override fun handle(e: Int?): Int = when(e) {

        else -> R.drawable.blicked_image
    }
}