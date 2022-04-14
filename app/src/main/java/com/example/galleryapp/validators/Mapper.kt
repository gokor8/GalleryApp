package com.example.galleryapp.validators

import android.content.res.Resources

interface ResourceMapper<R> {
    fun mapTo(resources: Resources) : R
}