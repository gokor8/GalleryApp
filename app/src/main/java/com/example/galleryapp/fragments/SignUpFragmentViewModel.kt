package com.example.galleryapp.fragments

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.core.ValidationTypes
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignUpEntity
import com.example.domain.usecases.RegistrationUseCase
import com.example.galleryapp.R
import com.example.galleryapp.ValidationHandler
import com.example.galleryapp.ui_displays.BaseValidationParser
import com.example.galleryapp.ui_displays.UISignUpEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpFragmentViewModel @Inject constructor(
    private val application: Application,
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {
    val usernameLiveData = MutableLiveData<String?>()
    val emailLiveData = MutableLiveData<String?>()
    val birthdayLiveData = MutableLiveData<String?>()
    val authViewModel = MutableLiveData<String>()

    private val validatorHandler = ValidationHandler()

    fun validate(
        str: String,
        validationType: ValidationTypes,
        postLiveData: MutableLiveData<String?>
    ) {
        if (str.isNotEmpty()) {
            val errorId = validatorHandler.findValidator(validationType).validate(str)?: R.string.empty_error
            postLiveData.postValue(application.resources.getString(errorId))
        }
    }

    fun trySignUp(uiSignUpEntity: UISignUpEntity) {
        viewModelScope.launch {

            val signUpEntity = SignUpEntity(
                uiSignUpEntity.username,
                uiSignUpEntity.password,
                uiSignUpEntity.birthday,
                uiSignUpEntity.email
            )

            val authState = registrationUseCase.registrationUser(signUpEntity)
            when(authState){
                is AuthState.Success ->  "Регистрация успешна".let(authViewModel::postValue)
                is AuthState.Error -> {
                    val errorsMap = BaseValidationParser().parse(authState.error)
                    errorsMap.forEach { errorMap ->
                        validationTypeToLiveData(errorMap)
                    }
                }
            }
        }
    }

    private fun validationTypeToLiveData(errorMap: Map.Entry<ValidationTypes, String>) {
        when (errorMap.key) {
            ValidationTypes.Email -> emailLiveData.postValue(errorMap.value)
            ValidationTypes.Username -> usernameLiveData.postValue(errorMap.value)
            //ValidationTypes.Date -> emailLiveData.postValue(errorMap.value)
        }
    }
}