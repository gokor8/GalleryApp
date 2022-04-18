package com.example.galleryapp.ui.fragments

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.core.ErrorType
import com.example.domain.entities.AuthState
import com.example.domain.usecases.AuthorizationUseCase
import com.example.galleryapp.R
import com.example.galleryapp.ui.models.UiSignInModel
import com.example.galleryapp.validators.entities.BaseErrorUiModel
import kotlinx.coroutines.launch

class SignInFragmentViewModel(
    private val authorizationUseCase: AuthorizationUseCase,
    application: Application
) : BaseViewModel(application),
    ValidationViewModel {

    private val res = application.resources
    val passwordValidationLength = 5

    val emailErrorLiveData = MutableLiveData<BaseErrorUiModel>()
    val passwordErrorLiveData = MutableLiveData<BaseErrorUiModel>()
    val signInResultViewModel = MutableLiveData<String>()

    fun trySignIn(uiSignInModel: UiSignInModel) {
        val errorLiveDates = listOf(
            emailErrorLiveData,
            passwordErrorLiveData
        )
        val withoutErrors = checkingErrors(errorLiveDates)

        if (!withoutErrors) {
            signInResultViewModel.value = res.getString(R.string.notify_check_all_fields)
            return
        }

        viewModelScope.launch {

            val authState = authorizationUseCase.authorization(uiSignInModel.mapTo())
            mapAuthState(authState)
        }
    }

    override fun mapAuthState(authState: AuthState) {
        when (authState) {
            is AuthState.Success -> {
                res.getString(R.string.notify_success_registration)
                    .let(signInResultViewModel::postValue)
            }
            is AuthState.Error -> {
                authState.errorMap.forEach { (errorType: ErrorType, errorMessage) ->
                    mapError(errorType, errorMessage)?.apply {
                        first.value = BaseErrorUiModel(second)
                    }
                }
            }
            is AuthState.Exception -> signInResultViewModel.value = authState.errorMessage
        }
    }

    override fun mapError(
        errorType: ErrorType,
        errorMessage: String
    ): Pair<MutableLiveData<BaseErrorUiModel>, String>? =
        when (errorType) {
            ErrorType.Password -> Pair(passwordErrorLiveData, errorMessage)
            ErrorType.Email -> Pair(emailErrorLiveData, errorMessage)
            else -> null
        }
}