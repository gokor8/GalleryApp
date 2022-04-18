package com.example.galleryapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentSignInBinding
import com.example.galleryapp.ui.models.UiSignInModel
import com.example.galleryapp.validators.validators_impl.EmailSingleValidator
import com.example.galleryapp.validators.validators_impl.LengthSingleValidator
import com.google.android.material.textfield.TextInputEditText

class SignInFragmentFocus :
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
        viewModel.emailErrorLiveData.observe(viewLifecycleOwner) {
            setError(binding.emailInputLayout, it)
        }

        viewModel.passwordErrorLiveData.observe(viewLifecycleOwner) {
            setError(binding.passwordTextInputLayout, it)
        }
    }

    override fun setListeners() {
        binding.apply {
            val emailInputEditText = emailInputLayout.editText as TextInputEditText
            val passwordInputEditText = passwordTextInputLayout.editText as TextInputEditText

            emailInputEditText.setBaseOnFocusChangeListener(viewModel.emailErrorLiveData) {
                viewModel.validate(
                    EmailSingleValidator(
                        emailInputEditText.text.toString(),
                        getString(R.string.error_email)
                    ),
                    it
                )
            }
            passwordInputEditText.setBaseOnFocusChangeListener(viewModel.passwordErrorLiveData) {
                viewModel.validate(
                    LengthSingleValidator(
                        viewModel.passwordValidationLength,
                        passwordInputEditText.text.toString(),
                        getString(R.string.error_password)
                    ),
                    it
                )
            }

            signInButton.setOnClickListener {
                UiSignInModel(
                    emailInputEditText.text.toString(),
                    passwordInputEditText.text.toString()
                )
            }
        }
    }

    override var lastValidationField: TextInputEditText? = null

}