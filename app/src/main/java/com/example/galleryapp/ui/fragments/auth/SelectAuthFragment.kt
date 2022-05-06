package com.example.galleryapp.ui.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.galleryapp.databinding.FragmentSelectAuthBinding

class SelectAuthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSelectAuthBinding.inflate(inflater, container, false)

        binding.signInButton.setOnClickListener {
            SelectAuthFragmentDirections.actionToSignInFragment()
                .let(findNavController()::navigate)
        }

        binding.signUpButton.setOnClickListener {
            SelectAuthFragmentDirections.actionToSignUpFragment()
                .let(findNavController()::navigate)
        }

        return binding.root
    }
}