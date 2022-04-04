package com.example.galleryapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.galleryapp.databinding.FragmentSignUpBinding
import com.example.galleryapp.ValidationHandler
import com.google.android.material.textfield.TextInputEditText

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

            viewModel?.let { vm ->
                val emailEditText = emailInputLayout.editText as TextInputEditText

                vm.emailLiveData.observe(viewLifecycleOwner) {
                    emailEditText.error = if(it==null) "" else getString(it)
                }

                emailEditText.setOnFocusChangeListener { _, b ->
                    if(b) return@setOnFocusChangeListener

                    vm.validate(
                        emailEditText.text.toString(),
                        ValidationHandler.ValidatorTypes.Email,
                        vm.emailLiveData
                    )
                }

                signUpButton.setOnClickListener {

                }
            }
        }

        return binding?.root
    }
}
