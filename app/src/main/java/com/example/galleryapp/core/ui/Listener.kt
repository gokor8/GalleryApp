package com.example.galleryapp.core.ui

import dagger.internal.SingleCheck

interface Listener {

    interface Save<I> {
        val listenValue: I?
    }

    interface Read<I> {
        fun observe(value: (I) -> Unit)
    }

}