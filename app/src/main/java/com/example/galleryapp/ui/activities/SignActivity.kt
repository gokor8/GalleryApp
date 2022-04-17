package com.example.galleryapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.galleryapp.databinding.ActivitySignBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignActivity : AppCompatActivity() {

    private var _binding: ActivitySignBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(_binding?.root)
    }
}