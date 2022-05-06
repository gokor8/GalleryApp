package com.example.galleryapp.ui.fragments.bnv

import com.example.galleryapp.core.ui.Listener

class HomeFragmentListener : Listener.Save<String>, Listener.Read<String> {
    override val listenValue: String? = null

    override fun observe(value: (String) -> Unit) {
        listenValue?.let(value)
    }
}