package com.example.galleryapp.ui.fragments

import android.app.Application
import androidx.lifecycle.*
import com.example.domain.core.ErrorType
import com.example.domain.entities.AuthState
import com.example.domain.usecases.RegistrationUseCase
import com.example.galleryapp.R
import com.example.galleryapp.ui.models.UISignUpModel
import com.example.galleryapp.validators.entities.ErrorUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpFragmentViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    fragmentApplication: Application,
) : AndroidViewModel(fragmentApplication), ValidationViewModel {

    private val res = fragmentApplication.resources

    val usernameErrorLiveData = MutableLiveData<ErrorUiModel>()
    val emailErrorLiveData = MutableLiveData<ErrorUiModel>()
    val birthdayErrorLiveData = MutableLiveData<ErrorUiModel>()
    val confirmPasswordErrorLiveData = MutableLiveData<ErrorUiModel>()
    val oldPasswordErrorLiveData = MutableLiveData<ErrorUiModel>()

    val signInResultViewModel = MutableLiveData<String>()

    val passwordValidationLength = 5

    fun trySignUp(uiSignUpModel: UISignUpModel) {
        val errorLiveDates = listOf(
            usernameErrorLiveData,
            emailErrorLiveData,
            confirmPasswordErrorLiveData,
            oldPasswordErrorLiveData
        )
        var withoutErrors = true

        errorLiveDates.forEach {
            if (it.value == null) {
                withoutErrors = false
            } else if (it.value!!.hasError) {
                withoutErrors = false
            }
        }

        if (!withoutErrors) {
            signInResultViewModel.value = res.getString(R.string.notify_check_all_fields)
            return
        }

        viewModelScope.launch {

            val authState = registrationUseCase.registrationUser(uiSignUpModel.mapTo())

            when (authState) {
                is AuthState.Success -> {
                    res.getString(R.string.notify_success_registration)
                        .let(signInResultViewModel::postValue)
                }
                is AuthState.Error -> {
                    authState.errorMap.forEach { (errorType: ErrorType, errorMessage) ->
                        val errorPair = mapError(errorType, errorMessage)
                        errorPair.first.value = ErrorUiModel(errorPair.second)
                    }
                }
                is AuthState.Exception -> signInResultViewModel.value = authState.errorMessage
            }
        }
    }

    private fun mapError(
        errorType: ErrorType,
        errorMessage: String
    ): Pair<MutableLiveData<ErrorUiModel>, String> =
        when (errorType) {
            ErrorType.Username -> Pair(usernameErrorLiveData, errorMessage)
            ErrorType.Email -> Pair(emailErrorLiveData, errorMessage)
        }
}