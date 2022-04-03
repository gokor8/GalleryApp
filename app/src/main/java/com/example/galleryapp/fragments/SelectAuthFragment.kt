package com.example.galleryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentSelectAuthBinding

class SelectAuthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSelectAuthBinding.inflate(inflater, container, false)

        binding.signInButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_to_signInFragment)
        }

        binding.signUpButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_to_signUpFragment)
        }

        return binding.root
    }
}