package com.example.galleryapp.ui.fragments.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentSignInBinding
import com.example.galleryapp.ui.activities.UserActivity
import com.example.galleryapp.ui.fragments.BaseFragment
import com.example.galleryapp.ui.fragments.FocusValidationFragment
import com.example.galleryapp.validators.validators_impl.EmailSingleValidator
import com.example.galleryapp.validators.validators_impl.LengthSingleValidator
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragmentFocus : BaseFragment<FragmentSignInBinding, SignInFragmentViewModel>(
    SignInFragmentViewModel::class.java,
    { inflater, container ->
        FragmentSignInBinding.inflate(inflater, container, false)
    }),
    FocusValidationFragment {

    override var lastValidationField: TextInputEditText? = null

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

        viewModel.signInResultViewModel.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }

        viewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            binding.importedProgressBar.waitProgressBarLayout.visibility = it.visibility
        }

        viewModel.authorizationLiveData.observe(viewLifecycleOwner) {
            val intent = Intent(activity, UserActivity::class.java)
            startActivity(intent)
            activity?.finish()
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
                lastValidationField?.clearFocus()

                UiSignInModel(
                    emailInputEditText.text.toString(),
                    passwordInputEditText.text.toString()
                ).let(viewModel::trySignIn)
            }
        }
    }
}