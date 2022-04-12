package com.example.galleryapp.fragments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.core.ValidationTypes
import com.example.domain.entities.AuthState
import com.example.domain.usecases.RegistrationUseCase
import com.example.galleryapp.R
import com.example.galleryapp.ValidationHandler
import com.example.galleryapp.ui_displays.BaseServerErrorParser
import com.example.galleryapp.ui_displays.UISignUpEntity
import com.example.galleryapp.validators.EmailValidator
import com.example.galleryapp.validators.PasswordValidator
import com.example.galleryapp.validators.PasswordsMultiDataValidator
import com.example.galleryapp.validators.Validators
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpFragmentViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    private val application: Application,
    private val validatorHandler: ValidationHandler
) : ViewModel() {

    private val _usernameErrorLiveData = MutableLiveData<String>()
    private val _emailErrorLiveData = MutableLiveData<String>()
    private val _birthdayErrorLiveData = MutableLiveData<String>()
    private val _passwordErrorLiveData = MutableLiveData<String>()
    private val _authViewModel = MutableLiveData<String>()

    val usernameErrorLiveData: LiveData<String> = _usernameErrorLiveData
    val emailErrorLiveData: LiveData<String> = _emailErrorLiveData
    val birthdayErrorLiveData: LiveData<String> = _birthdayErrorLiveData
    val confirmPasswordErrorLiveData: LiveData<String> = _passwordErrorLiveData
    val oldPasswordErrorLiveData = MutableLiveData<String>()
    val authViewModel: LiveData<String> = _authViewModel

    fun validate(
        validator: Validators,
    ) {
        if (validator.isNullData) {
            val errorId = validator.validate() ?: R.string.empty_error
            validatorToLiveData(
                validator,
                application.resources.getString(errorId)
            )
        }
    }

    fun validate(
        validator: Validators,
        liveData: MutableLiveData<String>
    ) {
        if (validator.isNullData) {
            val errorId = validator.validate() ?: R.string.empty_error
            application.resources.getString(errorId).let(liveData::postValue)
        }
    }

    fun trySignUp(uiSignUpEntity: UISignUpEntity) {
        viewModelScope.launch {

            val authState = registrationUseCase.registrationUser(uiSignUpEntity.mapTo())

            when (authState) {
                is AuthState.Success -> {
                    clearValidationErrors()
                    "Регистрация успешна".let(_authViewModel::postValue)
                }
                is AuthState.Error -> {
                    val errorsMap = BaseServerErrorParser().parse(authState.error)
                    errorsMap.forEach { errorMap ->
                        validatorToLiveData(errorMap.key as Validators, errorMap.value)
                        // errorMap.key as Validators могу избавиться от Validators унаследовавши все интерфейсы валидации от него
                    }
                }
            }
        }
    }

    private fun validatorToLiveData(validator: Validators, errorString: String) {
        when (validator) {
            is EmailValidator -> _emailErrorLiveData.postValue(errorString)
            is PasswordValidator -> _passwordErrorLiveData.postValue(errorString)
            is PasswordsMultiDataValidator -> {
                _passwordErrorLiveData.postValue(errorString)
                oldPasswordErrorLiveData.postValue(errorString)
            }
        }
    }

    private fun clearValidationErrors() {
        _emailErrorLiveData.postValue("")
        _emailErrorLiveData.postValue("")
        _birthdayErrorLiveData.postValue("")
        _passwordErrorLiveData.postValue("")
    }
}