package com.samkt.fodoo.screens.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.fodoo.utils.UiEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpScreenViewModel : ViewModel() {
    private val _signUpScreenState = MutableStateFlow(SignUpScreenState())
    val signUpScreenState = _signUpScreenState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvents>()
    val uiEvents = _uiEvent.asSharedFlow()

    fun onEvent(event: SignUpScreenEvents) {
        when (event) {
            is SignUpScreenEvents.OnConfirmPasswordChange -> {
                _signUpScreenState.update { state ->
                    state.copy(
                        confirmPassword = event.input,
                    )
                }
            }

            is SignUpScreenEvents.OnConfirmPasswordEyeToggle -> {
                _signUpScreenState.update { state ->
                    state.copy(
                        confirmPasswordVisible = !signUpScreenState.value.confirmPasswordVisible,
                    )
                }
            }

            is SignUpScreenEvents.OnEmailChange -> {
                _signUpScreenState.update { state ->
                    state.copy(
                        email = event.input,
                    )
                }
            }

            is SignUpScreenEvents.OnPasswordChange -> {
                _signUpScreenState.update { state ->
                    state.copy(
                        password = event.input,
                    )
                }
            }

            is SignUpScreenEvents.OnPasswordEyeToggle -> {
                _signUpScreenState.update { state ->
                    state.copy(
                        passwordVisible = !signUpScreenState.value.passwordVisible,
                    )
                }
            }

            is SignUpScreenEvents.OnSignUpClicked -> {
                signUpUser()
            }
        }
    }

    private fun signUpUser() {
        _signUpScreenState.value.apply {
            if (email.isEmpty()) {
                sendUiEvent(UiEvents.ShowSnackBar("Email cannot be empty!!"))
                return
            }
            if (password.isEmpty()) {
                sendUiEvent(UiEvents.ShowSnackBar("Password cannot be empty!!"))
                return
            }
            if (confirmPassword.isEmpty()) {
                sendUiEvent(UiEvents.ShowSnackBar("Confirm cannot be empty!!"))
                return
            }
            if (password != confirmPassword) {
                sendUiEvent(UiEvents.ShowSnackBar("Password and confirm password do not match!!"))
                return
            }
        }
    }

    private fun sendUiEvent(event: UiEvents) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
}

sealed interface SignUpScreenEvents {
    data class OnEmailChange(val input: String) : SignUpScreenEvents

    data class OnPasswordChange(val input: String) : SignUpScreenEvents

    data class OnConfirmPasswordChange(val input: String) : SignUpScreenEvents

    data object OnPasswordEyeToggle : SignUpScreenEvents

    data object OnConfirmPasswordEyeToggle : SignUpScreenEvents

    data object OnSignUpClicked : SignUpScreenEvents
}

data class SignUpScreenState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val confirmPasswordVisible: Boolean = false,
    val passwordVisible: Boolean = false,
)
