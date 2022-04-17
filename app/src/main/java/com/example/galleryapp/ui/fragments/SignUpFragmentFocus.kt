package com.example.galleryapp.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentSignUpBinding
import com.example.galleryapp.ui.models.UISignUpModel
import com.example.galleryapp.validators.ValidationChain
import com.example.galleryapp.validators.validators_impl.EmailSingleValidator
import com.example.galleryapp.validators.validators_impl.EmptyValidator
import com.example.galleryapp.validators.validators_impl.LengthSingleValidator
import com.example.galleryapp.validators.validators_impl.StringsMultiDataValidator
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragmentFocus : BaseFragment<FragmentSignUpBinding, SignUpFragmentViewModel>(),
    FocusValidationFragment {
    private var datePickerDialog: DatePickerDialog? = null
    override var lastValidationField: TextInputEditText? = null

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
                val usernameEditText = usernameInputLayout.editText as TextInputEditText
                val emailEditText = emailInputLayout.editText as TextInputEditText
                val birthdayEditText = birthdayInputLayout.editText as TextInputEditText

                vm.usernameErrorLiveData.observe(viewLifecycleOwner) {
                    setError(usernameInputLayout, it)
                }
                vm.emailErrorLiveData.observe(viewLifecycleOwner) {
                    setError(emailInputLayout, it)
                }
                vm.confirmPasswordErrorLiveData.observe(viewLifecycleOwner) {
                    setError(confirmPasswordInputLayout, it)
                }
                vm.oldPasswordErrorLiveData.observe(viewLifecycleOwner) {
                    setError(oldPasswordInputLayout, it)
                }

                vm.signInResultViewModel.observe(viewLifecycleOwner) {
                    if (it != null)
                        Snackbar.make(root, it, Snackbar.LENGTH_SHORT).show()
                }

                usernameEditText.setBaseOnFocusChangeListener(vm.usernameErrorLiveData) {
                    viewModel?.validate(
                        EmptyValidator(
                            usernameEditText.text.toString(),
                            getString(R.string.error_fill_blank)
                        ),
                        it
                    )
                }

                emailEditText.setBaseOnFocusChangeListener(
                    vm.emailErrorLiveData
                ) {
                    vm.validate(
                        EmailSingleValidator(
                            emailEditText.text.toString(),
                            getString(R.string.error_email)
                        ),
                        it
                    )
                }

                confirmPassword.setBaseOnFocusChangeListener(vm.confirmPasswordErrorLiveData) {
                    passwordValidation()
                }

                oldPassword.setBaseOnFocusChangeListener(vm.oldPasswordErrorLiveData) {
                    passwordValidation()
                }

                birthdayEditText.setOnClickListener {
                    datePickerDialog?.let {
                        if (it.isShowing)
                            return@setOnClickListener
                    }

                    context?.let { context ->
                        datePickerDialog = DatePickerDialog(
                            context
                        ).apply {
                            datePicker.maxDate = System.currentTimeMillis() - 1000
                            datePicker.setOnDateChangedListener { _, year, mounth, day ->
                                birthdayEditText.setText("$day.$mounth.$year")
                            }
                            show()
                        }
                    }
                }

                signUpButton.setOnClickListener {
                    lastValidationField?.clearFocus()

                    vm.trySignUp(
                        UISignUpModel(
                            username = username.text.toString(),
                            password = confirmPassword.text.toString(),
                            birthday = birthday.text.toString(),
                            email = email.text.toString()
                        )
                    )
                }
            }
        }

        return binding?.root
    }

    private fun passwordValidation() {
        binding?.apply {
            viewModel?.also { vm ->
                vm.validate(
                    ValidationChain(
                        LengthSingleValidator(
                            vm.passwordValidationLength,
                            confirmPassword.text.toString(),
                            getString(R.string.error_password)
                        ),
                        StringsMultiDataValidator(
                            listOf(
                                confirmPassword.text.toString(),
                                oldPassword.text.toString()
                            ), getString(R.string.error_passwords)
                        )
                    ), vm.confirmPasswordErrorLiveData
                )

                vm.validate(
                    ValidationChain(
                        LengthSingleValidator(
                            vm.passwordValidationLength,
                            oldPassword.text.toString(),
                            getString(R.string.error_password)
                        ),
                        StringsMultiDataValidator(
                            listOf(
                                confirmPassword.text.toString(),
                                oldPassword.text.toString()
                            ), getString(R.string.error_passwords)
                        )
                    ), vm.oldPasswordErrorLiveData
                )
            }
        }
    }
}
