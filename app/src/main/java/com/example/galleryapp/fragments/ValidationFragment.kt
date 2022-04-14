package com.example.galleryapp.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.galleryapp.validators.entities.ErrorEntity
import com.google.android.material.textfield.TextInputLayout

open class ValidationFragment<B : ViewBinding, V : ViewModel>() :
    BaseFragment<B, V>() {

    protected fun setErrorObserver(
        liveData: LiveData<ErrorEntity>,
        textInputLayout: TextInputLayout,
    ) {
        liveData.observe(viewLifecycleOwner) {
            setError(textInputLayout, it)
        }
    }

    protected fun setError(textInputLayout: TextInputLayout, errorEntity: ErrorEntity) {
        textInputLayout.error =
            errorEntity.getErrorIds().values.filterNotNull().joinToString(separator = "\r\n") { getString(it) }
        textInputLayout.isErrorEnabled = errorEntity.hasError
    }
}