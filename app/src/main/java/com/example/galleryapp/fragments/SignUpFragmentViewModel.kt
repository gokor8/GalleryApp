package com.example.galleryapp.fragments

import android.app.Application
import androidx.lifecycle.*
import com.example.domain.entities.AuthState
import com.example.domain.usecases.RegistrationUseCase
import com.example.galleryapp.R
import com.example.galleryapp.ui_displays.BaseServerErrorParser
import com.example.galleryapp.ui_displays.ErrorObserver
import com.example.galleryapp.ui_displays.UISignUpEntity
import com.example.galleryapp.validators.SingleValidator
import com.example.galleryapp.validators.Validator
import com.example.galleryapp.validators.entities.ErrorEntity
import com.example.galleryapp.validators.entities.FieldContainer
import com.example.galleryapp.validators.validators_impl.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpFragmentViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    private val fragmentApplication: Application,
) : AndroidViewModel(fragmentApplication) {

    private val errorObserver = ErrorObserver()

    val usernameErrorLiveData = MutableLiveData<ErrorEntity>()
    val emailErrorLiveData = MutableLiveData<ErrorEntity>()
    val birthdayErrorLiveData = MutableLiveData<ErrorEntity>()
    val passwordErrorLiveData = MutableLiveData<ErrorEntity>()
    val authViewModel = MutableLiveData<Int>()
    val oldPasswordErrorLiveData = MutableLiveData<ErrorEntity>()

    fun validate(
        validator: Validator,
        liveDataList: List<MutableLiveData<ErrorEntity>>
    ) {
        if (!validator.isNullData) {
            // val errorId = validator.validate() ?: R.string.empty_error
            liveDataList.forEach {
                val errorId = validator.validate()

                if(it.value != null) {
                    it.value!!.errorId.add(errorId)
                    it.postValue(ErrorEntity(it.value!!.errorId))
                } else {
                    it.postValue(ErrorEntity(arrayListOf(errorId)))
                }
            }
        }
    }

    /*fun validate(
        validator: Validator,
        liveData: MutableLiveData<ErrorEntity>
    ) {
        if (!validator.isNullData) {
            val errorId = validator.validate() ?: R.string.empty_error

            errorObserver.addOrRemove(
                liveData,
                ErrorEntity(fragmentApplication.resources.getString(errorId))
            ).let(liveData::postValue)
        } else {
            ErrorEntity(fragmentApplication.resources.getString(R.string.error_fill_blank))
                .let(liveData::postValue)
        }
    }*/

    fun trySignUp(uiSignUpEntity: UISignUpEntity) {
        val allErrorVm = listOf(
            usernameErrorLiveData,
            emailErrorLiveData,
            passwordErrorLiveData,
            oldPasswordErrorLiveData
        )
        var withoutErrors = true

        allErrorVm.forEach {
            if (it.value == null)
                withoutErrors = false
            else if (it.value!!.hasError)
                withoutErrors = false
        }

        if (!withoutErrors) {
            authViewModel.postValue(R.string.notify_check_all_fields)
            return
        }

        viewModelScope.launch {

            val authState = registrationUseCase.registrationUser(uiSignUpEntity.mapTo())

            when (authState) {
                is AuthState.Success -> {
                    //clearValidationErrors()
                    R.string.notify_success_registration.let(authViewModel::postValue)
                }
                is AuthState.Error -> {
                    val errorsMap =
                        BaseServerErrorParser(fragmentApplication).parse(authState.error)
                    errorsMap.forEach { errorMap ->
                        /* if (errorMap.key is Validator) {
                        validatorToLiveData(errorMap.key as Validator) {
                            it.postValue(ErrorEntity(errorMap.value))
                        }
                    }*/
                        // errorMap.key as Validator могу избавиться от Validator унаследовавши все интерфейсы валидации от него
                    }
                }
            }
        }
    }

   /* private fun validatorToLiveData(
        validator: Validator,
    ) {
        when (validator) {
            is PasswordsMultiDataValidator -> {

            }
            is PasswordSingleValidator -> {
                confirmPasswordContainer.addOrRemoveError(validator)
                passwordErrorLiveData.postValue(ErrorEntity(confirmPasswordContainer.getStringErrors() {
                    fragmentApplication.resources.getString(
                        it
                    )
                }))
            }
        }
    }*/

    private fun clearValidationErrors() {
        val noErrorEntity = ErrorEntity(arrayListOf<Int?>())

        emailErrorLiveData.postValue(noErrorEntity)
        emailErrorLiveData.postValue(noErrorEntity)
        birthdayErrorLiveData.postValue(noErrorEntity)
        passwordErrorLiveData.postValue(noErrorEntity)
    }
}