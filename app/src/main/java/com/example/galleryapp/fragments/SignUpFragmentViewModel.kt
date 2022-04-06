package com.example.galleryapp.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.UserRepository
import com.example.domain.entities.SignUpEntity
import com.example.galleryapp.ValidationHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpFragmentViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val emailLiveData = MutableLiveData<Int?>()
    val birthdayLiveData = MutableLiveData<Int?>()
    val authViewModel = MutableLiveData<Boolean>()
    private val validatorHandler = ValidationHandler()

    fun validate(
        str: String,
        validationType: ValidationHandler.ValidationTypes,
        postLiveData: MutableLiveData<Int?>
    ) {
        if (str.isNotEmpty())
            validatorHandler.findValidator(validationType).validate(str)
                .let(postLiveData::postValue)
    }

    fun trySignUp(signUpEntity: SignUpEntity) {
        viewModelScope.launch(Dispatchers.IO) {
                repository.createUser(signUpEntity).let(authViewModel::postValue)
            }
    }
}