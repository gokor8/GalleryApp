package com.example.galleryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.galleryapp.R
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