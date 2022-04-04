package com.example.galleryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.galleryapp.databinding.FragmentSignUpBinding

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpFragmentViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding?.apply {
            toolbarLayout.cancelTextView.setOnClickListener {
                it.findNavController().popBackStack()
            }

            signUpButton.setOnClickListener {

            }
        }

        return binding?.root
    }
}
