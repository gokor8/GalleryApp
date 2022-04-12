package com.example.galleryapp.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.textfield.TextInputLayout

open class ValidationFragment<B : ViewBinding, V : ViewModel>() :
    BaseFragment<B, V>() {

    protected fun setErrorObserver(
        liveData: LiveData<String>,
        textInputLayout: TextInputLayout,
    ) {
        liveData.observe(viewLifecycleOwner) {
            setError(textInputLayout, it)
        }
    }

    protected fun setError(textInputLayout: TextInputLayout, error: String) {
        textInputLayout.error = error

        if (error == "")
            textInputLayout.isErrorEnabled = false
    }
}