package com.samkt.fodoo.screens.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.samkt.fodoo.data.auth.Authentication
import com.samkt.fodoo.data.auth.Result
import com.samkt.fodoo.navigation.NavigationScreens
import com.samkt.fodoo.utils.UiEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpScreenViewModel : ViewModel() {
    private val _signUpScreenState = MutableStateFlow(SignUpScreenState())
    val signUpScreenState = _signUpScreenState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvents>()
    val uiEvents = _uiEvent.asSharedFlow()

    private val authentication = Authentication()

    init {
        authentication.setFirebaseAuth(FirebaseAuth.getInstance())
    }

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
                        email = event.input.lowercase(),
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

            _signUpScreenState.update {
                it.copy(
                    isSignUpLoading = true,
                )
            }

            authentication.signUpUser(email.trim(), password.trim()).onEach { result ->
                when (result) {
                    is Result.Success -> {
                        sendUiEvent(UiEvents.ShowSnackBar("Sign Up Successful!!"))
                        sendUiEvent(UiEvents.Navigate(NavigationScreens.HomeScreen.route))
                    }

                    is Result.Error -> {
                        _signUpScreenState.update {
                            it.copy(
                                isSignUpLoading = false,
                            )
                        }
                        sendUiEvent(UiEvents.ShowSnackBar(result.message ?: "Sign up failed!!"))
                    }
                }
            }.launchIn(viewModelScope)
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
    val isSignUpLoading: Boolean = false,
)
