package com.example.galleryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.galleryapp.databinding.FragmentSignUpBinding
import com.example.galleryapp.ValidationFactory

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpFragmentViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SignUpFragmentViewModel::class.java]

        binding?.apply {
            toolbarLayout.cancelTextView.setOnClickListener {
                it.findNavController().popBackStack()
            }

            val emailEditText = nameInputLayout.editText as EditText
            emailEditText.setOnFocusChangeListener { view, b ->
                viewModel?.let {
                    it.validate(
                        emailEditText.toString(),
                        ValidationFactory.ValidatorTypes.Email
                    )
                }
            }

            signUpButton.setOnClickListener {

            }
        }

        return binding?.root
    }
}
