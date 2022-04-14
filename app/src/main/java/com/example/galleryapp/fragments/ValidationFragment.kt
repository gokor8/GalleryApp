package com.example.galleryapp.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.galleryapp.R
import com.example.galleryapp.validators.entities.ErrorEntity
import com.example.galleryapp.validators.validators_impl.EmailSingleValidator
import com.google.android.material.textfield.TextInputLayout

open class ValidationFragment<B : ViewBinding, V : ViewModel>() :
    BaseFragment<B, V>() {

    protected fun setBaseOnFocusChangeListener(textInputLayout: TextInputLayout, otherLogic: (isFocused: Boolean) -> Unit){
        textInputLayout.setOnFocusChangeListener { _, isFocused ->
            if (isFocused) {
                return@setOnFocusChangeListener
            }

            viewModel?.also {
                it
            }

            otherLogic(isFocused)

        }
    }

    protected fun setErrorObserver(
        liveData: LiveData<ErrorEntity>,
        textInputLayout: TextInputLayout,
    ) {
        liveData.observe(viewLifecycleOwner) {
            setError(textInputLayout, it)
        }
    }

    protected fun setError(textInputLayout: TextInputLayout, errorEntity: ErrorEntity) {
        textInputLayout.error = errorEntity.errorMessage
        textInputLayout.isErrorEnabled = errorEntity.hasError
    }
}