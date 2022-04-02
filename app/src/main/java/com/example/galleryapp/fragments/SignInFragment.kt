package com.example.galleryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.galleryapp.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment<SignInFragment>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return if(binding != null) {
            binding.root
        }
        else
            null
    }
}