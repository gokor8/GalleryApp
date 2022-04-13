package com.example.galleryapp.fragments

import android.app.Application
import androidx.lifecycle.*
import com.example.domain.entities.AuthState
import com.example.domain.usecases.RegistrationUseCase
import com.example.galleryapp.R
import com.example.galleryapp.ui_displays.BaseServerErrorParser
import com.example.galleryapp.ui_displays.ErrorObserver
import com.example.galleryapp.ui_displays.UISignUpEntity
import com.example.galleryapp.validators.Validator
import com.example.galleryapp.validators.entities.ErrorEntity
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

    private val _usernameErrorLiveData = MutableLiveData<ErrorEntity>()
    private val _emailErrorLiveData = MutableLiveData<ErrorEntity>()
    private val _birthdayErrorLiveData = MutableLiveData<ErrorEntity>()
    private val _passwordErrorLiveData = MutableLiveData<ErrorEntity>()
    private val _authViewModel = MutableLiveData<Int>()

    val usernameErrorLiveData: LiveData<ErrorEntity> = _usernameErrorLiveData
    val emailErrorLiveData: LiveData<ErrorEntity> = _emailErrorLiveData
    val birthdayErrorLiveData: LiveData<ErrorEntity> = _birthdayErrorLiveData
    val confirmPasswordErrorLiveData: LiveData<ErrorEntity> = _passwordErrorLiveData
    val oldPasswordErrorLiveData = MutableLiveData<ErrorEntity>()
    val authViewModel: LiveData<Int> = _authViewModel

    fun validate(
        validator: Validator,
    ) {
        if (!validator.isNullData) {
            val errorId = validator.validate() ?: R.string.empty_error

            validatorToLiveData(
                validator
            ) {
                errorObserver.addOrRemove(
                    it,
                    ErrorEntity(fragmentApplication.resources.getString(errorId))
                ).let(it::postValue)
            }
        } else {
            validatorToLiveData(
                validator
            ) {
                errorObserver.addOrRemove(
                    it,
                    ErrorEntity(fragmentApplication.resources.getString(R.string.error_fill_blank))
                ).let(it::postValue)
            }
        }
    }

    fun validate(
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
    }

    fun trySignUp(uiSignUpEntity: UISignUpEntity) {
        val allErrorVm = listOf(
            _usernameErrorLiveData,
            _emailErrorLiveData,
            _passwordErrorLiveData,
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
            _authViewModel.postValue(R.string.notify_check_all_fields)
            return
        }

        viewModelScope.launch {

            val authState = registrationUseCase.registrationUser(uiSignUpEntity.mapTo())

            when (authState) {
                is AuthState.Success -> {
                    clearValidationErrors()
                    R.string.notify_success_registration.let(_authViewModel::postValue)
                }
                is AuthState.Error -> {
                    val errorsMap =
                        BaseServerErrorParser(fragmentApplication).parse(authState.error)
                    errorsMap.forEach { errorMap ->
                        if (errorMap.key is Validator) {
                            validatorToLiveData(errorMap.key as Validator){
                                it.postValue(ErrorEntity(errorMap.value))
                            }
                        }
                        // errorMap.key as Validator могу избавиться от Validator унаследовавши все интерфейсы валидации от него
                    }
                }
            }
        }
    }

    private inline fun validatorToLiveData(
        validator: Validator,
        lifeDataDo: (liveData: MutableLiveData<ErrorEntity>) -> Unit
    ) {
        when (validator) {
            is UsernameParsableValidator -> lifeDataDo(_usernameErrorLiveData)
            is EmailSingleValidator -> lifeDataDo(_emailErrorLiveData)
            is PasswordSingleValidator -> lifeDataDo(_passwordErrorLiveData)
            is PasswordsMultiDataValidator -> {
                lifeDataDo(_passwordErrorLiveData)
                lifeDataDo(oldPasswordErrorLiveData)
            }
        }
    }

    private fun clearValidationErrors() {
        val noErrorEntity = ErrorEntity("")

        _emailErrorLiveData.postValue(noErrorEntity)
        _emailErrorLiveData.postValue(noErrorEntity)
        _birthdayErrorLiveData.postValue(noErrorEntity)
        _passwordErrorLiveData.postValue(noErrorEntity)
    }
}