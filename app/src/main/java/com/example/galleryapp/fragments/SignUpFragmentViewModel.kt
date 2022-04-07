package com.example.galleryapp.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.SignUpEntity
import com.example.domain.usecases.RegistrationUseCase
import com.example.galleryapp.ValidationHandler
import com.example.galleryapp.ui_ntities.UISignUpEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class SignUpFragmentViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase) : ViewModel() {

    val emailLiveData = MutableLiveData<Int?>()
    val birthdayLiveData = MutableLiveData<Int?>()
    val authViewModel = MutableLiveData<String>()

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

    fun trySignUp(uiSignUpEntity: UISignUpEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            /*val sourceDateFormat = SimpleDateFormat("dd.MM.YYYY")
            val srcBirthday = sourceDateFormat.parse(uiSignUpEntity.birthday)
            val destBirthday = sourceDateFormat.format(srcBirthday)*/

            val signUpEntity = SignUpEntity(
                uiSignUpEntity.username,
                uiSignUpEntity.password,
                uiSignUpEntity.birthday,
                uiSignUpEntity.email
            )

            val message = registrationUseCase.registrationUser(signUpEntity)
            if (message == null) {
                "Регистрация успешна".let(authViewModel::postValue)
            } else {
                message.let(authViewModel::postValue)
            }
        }
    }
}