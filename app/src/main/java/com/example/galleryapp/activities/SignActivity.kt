package com.example.galleryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.galleryapp.R

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_GalleryApp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
    }
}