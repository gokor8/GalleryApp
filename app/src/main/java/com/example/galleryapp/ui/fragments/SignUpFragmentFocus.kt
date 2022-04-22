package com.example.galleryapp.ui.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentSignUpBinding
import com.example.galleryapp.ui.activities.UserActivity
import com.example.galleryapp.ui.models.UiSignUpModel
import com.example.galleryapp.validators.ValidationChain
import com.example.galleryapp.validators.validators_impl.EmailSingleValidator
import com.example.galleryapp.validators.validators_impl.EmptyValidator
import com.example.galleryapp.validators.validators_impl.LengthSingleValidator
import com.example.galleryapp.validators.validators_impl.StringsMultiDataValidator
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragmentFocus :
    BaseFragment<FragmentSignUpBinding, SignUpFragmentViewModel>(SignUpFragmentViewModel::class.java),
    FocusValidationFragment {
    private var datePickerDialog: DatePickerDialog? = null
    override var lastValidationField: TextInputEditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
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
        viewModel.usernameErrorLiveData.observe(viewLifecycleOwner) {
            setError(binding.usernameInputLayout, it)
        }
        viewModel.emailErrorLiveData.observe(viewLifecycleOwner) {
            setError(binding.emailInputLayout, it)
        }
        viewModel.confirmPasswordErrorLiveData.observe(viewLifecycleOwner) {
            setError(binding.confirmPasswordInputLayout, it)
        }
        viewModel.oldPasswordErrorLiveData.observe(viewLifecycleOwner) {
            setError(binding.oldPasswordInputLayout, it)
        }

        viewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            binding.waitProgressBarLayout.visibility = it.visibility
        }

        viewModel.registrationLiveData.observe(viewLifecycleOwner) {
            val intent = Intent(activity, UserActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        viewModel.signInResultViewModel.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun setListeners() {

        binding.apply {
            val usernameEditText = usernameInputLayout.editText as TextInputEditText
            val emailEditText = emailInputLayout.editText as TextInputEditText
            val birthdayEditText = birthdayInputLayout.editText as TextInputEditText

            usernameEditText.setBaseOnFocusChangeListener(viewModel.usernameErrorLiveData) {
                viewModel.validate(
                    EmptyValidator(
                        usernameEditText.text.toString(),
                        getString(R.string.error_fill_blank)
                    ),
                    it
                )
            }

            emailEditText.setBaseOnFocusChangeListener(
                viewModel.emailErrorLiveData
            ) {
                viewModel.validate(
                    EmailSingleValidator(
                        emailEditText.text.toString(),
                        getString(R.string.error_email)
                    ),
                    it
                )
            }

            confirmPassword.setBaseOnFocusChangeListener(viewModel.confirmPasswordErrorLiveData) {
                passwordValidation()
            }

            oldPassword.setBaseOnFocusChangeListener(viewModel.oldPasswordErrorLiveData) {
                passwordValidation()
            }

            birthdayEditText.setOnClickListener {
                datePickerDialog?.let {
                    if (it.isShowing)
                        return@setOnClickListener
                }

                datePickerDialog = DatePickerDialog(
                    requireContext()
                ).apply {
                    setButton(DatePickerDialog.BUTTON_POSITIVE, "Ok", this)
                    datePicker.maxDate = System.currentTimeMillis() - 1000
                    datePicker.setOnDateChangedListener { _, year, mounth, day ->
                        birthdayEditText.setText("$day.$mounth.$year")
                    }
                    show()
                }
            }

            signUpButton.setOnClickListener {
                lastValidationField?.clearFocus()

                viewModel.trySignUp(
                    UiSignUpModel(
                        username = username.text.toString(),
                        password = confirmPassword.text.toString(),
                        birthday = birthday.text.toString(),
                        email = email.text.toString()
                    )
                )
            }
        }
    }

    private fun passwordValidation() {
        viewModel.validate(
            ValidationChain(
                LengthSingleValidator(
                    viewModel.passwordValidationLength,
                    binding.confirmPassword.text.toString(),
                    getString(R.string.error_password)
                ),
                StringsMultiDataValidator(
                    listOf(
                        binding.confirmPassword.text.toString(),
                        binding.oldPassword.text.toString()
                    ), getString(R.string.error_passwords)
                )
            ),
            viewModel.confirmPasswordErrorLiveData
        )

        viewModel.validate(
            ValidationChain(
                LengthSingleValidator(
                    viewModel.passwordValidationLength,
                    binding.oldPassword.text.toString(),
                    getString(R.string.error_password)
                ),
                StringsMultiDataValidator(
                    listOf(
                        binding.confirmPassword.text.toString(),
                        binding.oldPassword.text.toString()
                    ), getString(R.string.error_passwords)
                )
            ),
            viewModel.oldPasswordErrorLiveData
        )
    }
}
