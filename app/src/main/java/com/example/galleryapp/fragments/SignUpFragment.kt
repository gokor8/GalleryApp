package com.example.galleryapp.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                val birthdayEditText = birthdayInputLayout.editText as TextInputEditText

                vm.emailLiveData.observe(viewLifecycleOwner) {
                    emailInputLayout.error = if(it==null) "" else getString(it)
                }

                vm.birthdayLiveData.observe(viewLifecycleOwner) {
                    birthdayInputLayout.error = if(it==null) "" else getString(it)
                }

                emailEditText.setOnFocusChangeListener { _, isFocused ->
                    if(isFocused) return@setOnFocusChangeListener

                    vm.validate(
                        emailEditText.text.toString(),
                        ValidationHandler.ValidationTypes.Email,
                        vm.emailLiveData
                    )
                }

                birthdayEditText.setOnFocusChangeListener { _, isFocused ->
                    if(isFocused) return@setOnFocusChangeListener

                    vm.validate(
                        birthdayEditText.text.toString(),
                        ValidationHandler.ValidationTypes.Date,
                        vm.birthdayLiveData
                    )
                }

                signUpButton.setOnClickListener {
                    val isEmpty = name.text!!.isNotEmpty()
                            && birthday.text!!.isNotEmpty()
                        && email.text!!.isNotEmpty() && oldPassword.text!!.isNotEmpty() &&
                        confirmPassword.text!!.isNotEmpty()

                    val hasErrors =  name.error.isEmpty() && birthday.error.isEmpty()
                            && email.error.isEmpty() && oldPassword.error.isEmpty() &&
                            confirmPassword.error.isEmpty()

                    if(!isEmpty && hasErrors){

                    }
                    else
                        signUpButton.setBackgroundColor(Color.RED)
                }
            }
        }

        return binding?.root
    }
}
