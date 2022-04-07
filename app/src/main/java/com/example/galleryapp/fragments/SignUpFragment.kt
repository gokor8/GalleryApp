package com.example.galleryapp.fragments

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.domain.entities.SignUpEntity
import com.example.galleryapp.databinding.FragmentSignUpBinding
import com.example.galleryapp.ValidationHandler
import com.example.galleryapp.ui_ntities.UISignUpEntity
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
                val emailEditText = emailInputLayout.editText as TextInputEditText
                val birthdayEditText = birthdayInputLayout.editText as TextInputEditText

                vm.emailLiveData.observe(viewLifecycleOwner) {
                    emailInputLayout.error = if (it == null) "" else getString(it)
                }

                vm.birthdayLiveData.observe(viewLifecycleOwner) {
                    birthdayInputLayout.error = if (it == null) "" else getString(it)
                }

                vm.authViewModel.observe(viewLifecycleOwner) {
                    if (it != null)
                        Snackbar.make(root, it, Snackbar.LENGTH_SHORT).show()
                }

                emailEditText.setOnFocusChangeListener { _, isFocused ->
                    if (isFocused) return@setOnFocusChangeListener

                    vm.validate(
                        emailEditText.text.toString(),
                        ValidationHandler.ValidationTypes.Email,
                        vm.emailLiveData
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
                    val isNotEmpty = name.text!!.isNotEmpty()
                            && birthday.text!!.isNotEmpty()
                            && email.text!!.isNotEmpty() && oldPassword.text!!.isNotEmpty() &&
                            confirmPassword.text!!.isNotEmpty()

                    val withoutErrors = name.error.isNullOrEmpty() && birthday.error.isNullOrEmpty()
                            && email.error.isNullOrEmpty() && oldPassword.error.isNullOrEmpty() &&
                            confirmPassword.error.isNullOrEmpty()

                    if (isNotEmpty && withoutErrors) {
                        vm.trySignUp(
                            UISignUpEntity(
                                username = name.text.toString(),
                                password = confirmPassword.text.toString(),
                                birthday = birthday.text.toString(),
                                email = email.text.toString()
                            )
                        )
                    } else
                        signUpButton.setBackgroundColor(Color.RED)
                }
            }
        }

        return binding?.root
    }
}
