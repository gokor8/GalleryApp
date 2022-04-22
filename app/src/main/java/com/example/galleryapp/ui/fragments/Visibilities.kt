package com.example.galleryapp.ui.fragments

import android.view.View


enum class Visibilities(val visibility: Int) {
    Visible(View.VISIBLE), Invisible(View.INVISIBLE), Gone(View.GONE)
}