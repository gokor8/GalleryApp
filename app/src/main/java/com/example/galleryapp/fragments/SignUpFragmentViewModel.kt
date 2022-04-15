package com.example.galleryapp.fragments

import android.app.Application
import androidx.lifecycle.*
import com.example.domain.core.ErrorType
import com.example.domain.entities.AuthState
import com.example.domain.usecases.RegistrationUseCase
import com.example.galleryapp.R
import com.example.galleryapp.ui_displays.UISignUpEntity
import com.example.galleryapp.validators.entities.ErrorEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpFragmentViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    fragmentApplication: Application,
) : AndroidViewModel(fragmentApplication), ValidationViewModel {

    private val res = fragmentApplication.resources

    val usernameErrorLiveData = MutableLiveData<ErrorEntity>()
    val emailErrorLiveData = MutableLiveData<ErrorEntity>()
    val birthdayErrorLiveData = MutableLiveData<ErrorEntity>()
    val confirmPasswordErrorLiveData = MutableLiveData<ErrorEntity>()
    val authViewModel = MutableLiveData<Int>()
    val oldPasswordErrorLiveData = MutableLiveData<ErrorEntity>()

    val passwordValidationLength = 5

    // Тут пока ничего не менял по логике со старой архитектуры валдиации
    fun trySignUp(uiSignUpEntity: UISignUpEntity) {
        val allErrorVm = listOf(
            usernameErrorLiveData,
            emailErrorLiveData,
            confirmPasswordErrorLiveData,
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
                    R.string.notify_success_registration.let(authViewModel::postValue)
                }
                is AuthState.Error -> {
                    authState.errorMap.forEach { errorType: ErrorType, errorMessage ->
                        val errorPair = mapError(errorType, errorMessage)
                        errorPair.first.value = ErrorEntity(errorPair.second)
                    }
                }
            }
        }
    }

    private fun mapError(errorType: ErrorType, errorMessage: String): Pair<MutableLiveData<ErrorEntity>, String> =
        when (errorType) {
            ErrorType.Username -> Pair(usernameErrorLiveData, res.getString(R.string.cloud_error_username))
            ErrorType.Email -> Pair(emailErrorLiveData, res.getString(R.string.cloud_error_email))
        }
}