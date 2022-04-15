package com.example.galleryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.galleryapp.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment<FragmentSignInBinding, SignInFragmentViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding?.root
    }

// TODO
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupListeners()
//    }
//
//    fun setupListeners() {
//        binding?.apply {
//            toolbarLayout.cancelTextView.setOnClickListener {
//                it.findNavController().popBackStack()
//            }
//        }
//    }

}