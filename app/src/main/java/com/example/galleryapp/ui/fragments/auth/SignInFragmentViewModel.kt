package com.example.galleryapp.ui.fragments.auth

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.core.ServerErrorType
import com.example.domain.entities.states.AuthState
import com.example.domain.usecases.AuthorizationUseCase
import com.example.galleryapp.R
import com.example.galleryapp.ui.fragments.BaseViewModel
import com.example.galleryapp.ui.fragments.ValidationViewModel
import com.example.galleryapp.ui.fragments.Visibilities
import com.example.galleryapp.validators.entities.BaseErrorUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInFragmentViewModel @Inject constructor(
    private val authorizationUseCase: AuthorizationUseCase,
    application: Application
) : BaseViewModel(application), ValidationViewModel {

    private val res = application.resources
    val passwordValidationLength = 5

    val emailErrorLiveData = MutableLiveData<BaseErrorUiModel>()
    val passwordErrorLiveData = MutableLiveData<BaseErrorUiModel>()
    val signInResultViewModel = MutableLiveData<String>()
    private val _progressBarLiveData = MutableLiveData<Visibilities>()
    private val _authorizationLiveData = MutableLiveData<Unit>()
    val authorizationLiveData: LiveData<Unit>
        get() = _authorizationLiveData
    val progressBarLiveData: LiveData<Visibilities>
        get() = _progressBarLiveData

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
            _progressBarLiveData.value = Visibilities.Visible
            val authState = authorizationUseCase.authorization(uiSignInModel.mapTo())
            mapAuthState(authState)
        }
    }

    override fun mapAuthState(authState: AuthState) {
        _progressBarLiveData.value = Visibilities.Invisible
        when (authState) {
            is AuthState.Success -> {
                res.getString(R.string.notify_success_sign_in)
                    .let(signInResultViewModel::postValue)
                _authorizationLiveData.notifyObserver()
            }
            is AuthState.Error -> {
                authState.errorsContainer.errorsMap.forEach { (serverErrorType: ServerErrorType, errorMessage) ->
                    mapError(serverErrorType, errorMessage)?.apply {
                        first.value = BaseErrorUiModel(second)
                    }
                }
            }
            is AuthState.Exception -> signInResultViewModel.value = authState.failModel.getMessage()
        }
    }

    override fun mapError(
        serverErrorType: ServerErrorType,
        errorMessage: String
    ): Pair<MutableLiveData<BaseErrorUiModel>, String>? =
        when (serverErrorType) {
            ServerErrorType.Password -> Pair(passwordErrorLiveData, errorMessage)
            ServerErrorType.Email -> Pair(emailErrorLiveData, errorMessage)
            else -> null
        }
}