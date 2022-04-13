package com.example.galleryapp.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentSignUpBinding
import com.example.galleryapp.ui_displays.UISignUpEntity
import com.example.galleryapp.validators.validators_impl.EmailSingleValidator
import com.example.galleryapp.validators.validators_impl.PasswordSingleValidator
import com.example.galleryapp.validators.validators_impl.PasswordsMultiDataValidator
import com.example.galleryapp.validators.validators_impl.UsernameParsableValidator
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : ValidationFragment<FragmentSignUpBinding, SignUpFragmentViewModel>() {

    private var datePickerDialog: DatePickerDialog? = null
    private var lastValidationField: TextInputEditText? = null

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

                setErrorObserver(vm.usernameErrorLiveData, usernameInputLayout)
                setErrorObserver(vm.emailErrorLiveData, emailInputLayout)
                setErrorObserver(vm.confirmPasswordErrorLiveData, confirmPasswordInputLayout)
                setErrorObserver(vm.oldPasswordErrorLiveData, oldPasswordInputLayout)

                vm.authViewModel.observe(viewLifecycleOwner) {
                    if (it != null)
                        Snackbar.make(root, it, Snackbar.LENGTH_SHORT).show()
                }

                emailEditText.setOnFocusChangeListener { _, isFocused ->
                    if (isFocused) {
                        lastValidationField = emailEditText
                        return@setOnFocusChangeListener
                    }

                    vm.validate(
                        EmailSingleValidator(emailEditText.text.toString())
                    )
                }

                usernameEditText.setOnFocusChangeListener { _, isFocused ->
                    if (isFocused)
                        return@setOnFocusChangeListener

                    vm.validate(
                        UsernameParsableValidator(usernameEditText.text.toString()),
                    )
                }

                confirmPassword.setOnFocusChangeListener { _, isFocused ->
                    if (isFocused) return@setOnFocusChangeListener

                    vm.validate(
                        PasswordSingleValidator(confirmPassword.text.toString())
                    )
                }

                oldPassword.setOnFocusChangeListener { _, isFocused ->
                    if (isFocused) return@setOnFocusChangeListener

                    vm.validate(
                        PasswordSingleValidator(oldPassword.text.toString()),
                        vm.oldPasswordErrorLiveData
                    )
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
                    //val isLastFieldCorrect = vm.validate()
                    vm.validate(
                        PasswordsMultiDataValidator(
                            listOf(
                                oldPassword.text.toString(),
                                confirmPassword.text.toString()
                            )
                        )
                    )

                    vm.trySignUp(
                        UISignUpEntity(
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
}
