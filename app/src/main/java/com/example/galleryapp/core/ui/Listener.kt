package com.example.galleryapp.core.ui

import dagger.internal.SingleCheck

interface Listener {

    interface Save<I> {
        var listenValue: I?
    }

    interface Read<I> {
        fun<E> observe(entity: E, value: (I) -> Unit)

        fun<E> unsubscribe(entity: E)
    }
}