package com.example.galleryapp.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.domain.entities.SignUpEntity
import com.example.galleryapp.databinding.FragmentSignUpBinding
import com.example.galleryapp.ValidationHandler
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                    val isNotEmpty = name.text!!.isNotEmpty()
                            && birthday.text!!.isNotEmpty()
                        && email.text!!.isNotEmpty() && oldPassword.text!!.isNotEmpty() &&
                        confirmPassword.text!!.isNotEmpty()

                    val withoutErrors =  name.error.isNullOrEmpty() && birthday.error.isNullOrEmpty()
                            && email.error.isNullOrEmpty() && oldPassword.error.isNullOrEmpty() &&
                            confirmPassword.error.isNullOrEmpty()

                    if(isNotEmpty && withoutErrors){
                        vm.trySignUp(
                            SignUpEntity(
                                name.text.toString(),
                                confirmPassword.text.toString(),
                                birthday.text.toString(),
                                email.text.toString()
                            )
                        )
                    }
                    else
                        signUpButton.setBackgroundColor(Color.RED)
                }
            }
        }

        return binding?.root
    }
}
