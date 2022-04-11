package com.example.galleryapp.fragments

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.domain.core.ValidationTypes
import com.example.galleryapp.databinding.FragmentSignUpBinding
import com.example.galleryapp.ui_displays.UISignUpEntity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpFragmentViewModel>() {

    private var datePickerDialog: DatePickerDialog? = null

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
                    checkErrorTextInputLayout(usernameInputLayout, it)
                }

                vm.emailErrorLiveData.observe(viewLifecycleOwner) {
                    checkErrorTextInputLayout(emailInputLayout, it)
                }


                vm.passwordErrorLiveData.observe(viewLifecycleOwner) {
                    checkErrorTextInputLayout(confirmPasswordInputLayout, it)
                }

                vm.authViewModel.observe(viewLifecycleOwner) {
                    if (it != null)
                        Snackbar.make(root, it, Snackbar.LENGTH_SHORT).show()
                }

                emailEditText.setOnFocusChangeListener { _, isFocused ->
                    if (isFocused) return@setOnFocusChangeListener

                    vm.validate(
                        emailEditText.text.toString(),
                        ValidationTypes.Email,
                    )
                }

                /*usernameEditText.setOnFocusChangeListener { _, isFocused ->
                    if (isFocused) return@setOnFocusChangeListener

                    vm.validate(
                        usernameEditText.text.toString(),
                        ValidationTypes.Username,
                    )
                }*/

                confirmPassword.setOnFocusChangeListener { _, isFocused ->
                    if (isFocused) return@setOnFocusChangeListener

                    vm.validate(
                        confirmPassword.text.toString(),
                        ValidationTypes.Password,
                    )
                }


                birthdayEditText.setOnClickListener {
                    datePickerDialog?.let {
                        if(it.isShowing)
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
                    val isNotEmpty = username.text.toString().isNotEmpty()
                            && birthday.text.toString().isNotEmpty()
                            && email.text.toString().isNotEmpty()
                            && oldPassword.text.toString().isNotEmpty()
                            && confirmPassword.text.toString().isNotEmpty()

                    val withErrors = usernameInputLayout.isErrorEnabled
                            || birthdayInputLayout.isErrorEnabled
                            || emailInputLayout.isErrorEnabled
                            || oldPasswordInputLayout.isErrorEnabled
                            || confirmPasswordInputLayout.isErrorEnabled

                    if (isNotEmpty && !withErrors) {
                        vm.trySignUp(
                            UISignUpEntity(
                                username = username.text.toString(),
                                password = confirmPassword.text.toString(),
                                birthday = birthday.text.toString(),
                                email = email.text.toString()
                            )
                        )
                    } else
                        Snackbar.make(it, "Fill all fields!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        return binding?.root
    }
}
