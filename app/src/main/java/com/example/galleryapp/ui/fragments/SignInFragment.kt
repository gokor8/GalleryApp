package com.example.galleryapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.galleryapp.databinding.FragmentSignInBinding
import com.google.android.material.textfield.TextInputEditText

class SignInFragment :
    BaseFragment<FragmentSignInBinding, SignInFragmentViewModel>(
        SignInFragmentViewModel::class.java
    ), FocusValidationFragment {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarLayout.cancelTextView.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    override fun setObservers() {

    }

    override fun setListeners() {

    }

    override var lastValidationField: TextInputEditText? = null

}