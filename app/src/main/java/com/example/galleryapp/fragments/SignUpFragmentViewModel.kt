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
    val passwordErrorLiveData: LiveData<String> = _passwordErrorLiveData
    val oldPasswordErrorLiveData = MutableLiveData<String>()
    val authViewModel: LiveData<String> = _authViewModel

    fun trySignUp(uiSignUpEntity: UISignUpEntity) {
        viewModelScope.launch {

            val authState = registrationUseCase.registrationUser(uiSignUpEntity.mapTo())

            when(authState){
                is AuthState.Success ->  {
                    clearValidationErrors()
                    "Регистрация успешна".let(_authViewModel::postValue)
                }
                is AuthState.Error -> {
                    val errorsMap = BaseServerErrorParser().parse(authState.error)
                    errorsMap.forEach { errorMap ->
                        validationTypeToLiveData(errorMap.key, errorMap.value)
                    }
                }
            }
        }
    }

    fun validate(
        str: String,
        validationType: ValidationTypes
    ) {
        if (str.isNotEmpty()) {
            validatorHandler.findValidator(validationType)?.run {
                val errorId = validate(str) ?: R.string.empty_error
                    validationTypeToLiveData(
                        validationType,
                        application.resources.getString(errorId)
                    )
                }
        }
    }

    fun validate(
        str: String,
        validationType: ValidationTypes,
        liveData: MutableLiveData<String>
    ) {
        if (str.isNotEmpty()) {
            validatorHandler.findValidator(validationType)?.run {
                val errorId = validate(str) ?: R.string.empty_error
                application.resources.getString(errorId).let(liveData::postValue)
            }
        }
    }

    private fun validationTypeToLiveData(validationType: ValidationTypes, errorString: String) {
        when (validationType) {
            ValidationTypes.Email -> _emailErrorLiveData.postValue(errorString)
            ValidationTypes.Username -> _usernameErrorLiveData.postValue(errorString)
            ValidationTypes.Password -> _passwordErrorLiveData.postValue(errorString)
        }
    }

    private fun clearValidationErrors(){
        _emailErrorLiveData.postValue("")
        _emailErrorLiveData.postValue("")
        _birthdayErrorLiveData.postValue("")
        _passwordErrorLiveData.postValue("")
    }
}