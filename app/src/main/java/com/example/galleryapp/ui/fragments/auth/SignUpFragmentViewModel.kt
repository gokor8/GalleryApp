package com.example.galleryapp.ui.fragments.auth

import android.app.Application
import androidx.lifecycle.*
import com.example.domain.core.ErrorType
import com.example.domain.entities.states.AuthState
import com.example.domain.usecases.RegistrationUseCase
import com.example.galleryapp.R
import com.example.galleryapp.ui.fragments.BaseViewModel
import com.example.galleryapp.ui.fragments.ValidationViewModel
import com.example.galleryapp.ui.fragments.Visibilities
import com.example.galleryapp.ui.models.UiSignUpModel
import com.example.galleryapp.validators.entities.BaseErrorUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpFragmentViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    application: Application,
) : BaseViewModel(application), ValidationViewModel {

    private val res = application.resources

    val usernameErrorLiveData = MutableLiveData<BaseErrorUiModel>()
    val emailErrorLiveData = MutableLiveData<BaseErrorUiModel>()
    val birthdayErrorLiveData = MutableLiveData<BaseErrorUiModel>()
    val confirmPasswordErrorLiveData = MutableLiveData<BaseErrorUiModel>()
    val oldPasswordErrorLiveData = MutableLiveData<BaseErrorUiModel>()
    private val _progressBarLiveData = MutableLiveData<Visibilities>()
    private val _registrationLiveData = MutableLiveData<Unit>()
    val registrationLiveData: LiveData<Unit>
        get() = _registrationLiveData
    val progressBarLiveData: LiveData<Visibilities>
        get() = _progressBarLiveData

    val signInResultViewModel = MutableLiveData<String>()

    val passwordValidationLength = 5

    fun trySignUp(uiSignUpModel: UiSignUpModel) {
        val errorLiveDates = listOf(
            usernameErrorLiveData,
            emailErrorLiveData,
            confirmPasswordErrorLiveData,
            oldPasswordErrorLiveData
        )
        var withoutErrors = checkingErrors(errorLiveDates)

        if (!withoutErrors) {
            signInResultViewModel.value = res.getString(R.string.notify_check_all_fields)
            return
        }

        viewModelScope.launch {
            _progressBarLiveData.value = Visibilities.Visible
            val authState = registrationUseCase.registrationUser(uiSignUpModel.mapTo())
            mapAuthState(authState)
        }
    }

    override fun mapAuthState(authState: AuthState) {
        _progressBarLiveData.value = Visibilities.Invisible
         when (authState) {
            is AuthState.Success -> {
                res.getString(R.string.notify_success_registration)
                    .let(signInResultViewModel::postValue)
                _registrationLiveData.notifyObserver()
            }
            is AuthState.Error -> {
                authState.errorMap.forEach { (errorType: ErrorType, errorMessage) ->
                    mapError(errorType, errorMessage)?.apply {
                        first.value = BaseErrorUiModel(second)
                    }
                }
            }
            is AuthState.Exception -> signInResultViewModel.value = authState.failModel.getMessage()
        }
    }

    override fun mapError(
        errorType: ErrorType,
        errorMessage: String
    ): Pair<MutableLiveData<BaseErrorUiModel>, String>? =
        when (errorType) {
            ErrorType.Username -> Pair(usernameErrorLiveData, errorMessage)
            ErrorType.Email -> Pair(emailErrorLiveData, errorMessage)
            else -> null
        }
}