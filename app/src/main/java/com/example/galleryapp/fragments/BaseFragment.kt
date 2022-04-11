package com.example.galleryapp.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.textfield.TextInputLayout

open class BaseFragment<B : ViewBinding, V : ViewModel> : Fragment() {

    protected var binding: B? = null
    protected var viewModel: V? = null

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    protected fun checkErrorTextInputLayout(textInputLayout: TextInputLayout, error: String){
        textInputLayout.error = error

        if(error == "")
            textInputLayout.isErrorEnabled = false
    }
}