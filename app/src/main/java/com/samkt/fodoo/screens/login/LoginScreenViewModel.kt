package com.samkt.fodoo.screens.login

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
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {
    private val _loginScreenState = MutableStateFlow(LoginScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvents>()
    val uiEvents = _uiEvent.asSharedFlow()

    private val authentication = Authentication()

    init {
        authentication.setFirebaseAuth(FirebaseAuth.getInstance())
    }

    fun onEvent(event: LoginScreenEvents) {
        when (event) {
            is LoginScreenEvents.OnEmailChange -> {
                _loginScreenState.update { state ->
                    state.copy(
                        email = event.value,
                    )
                }
            }
            is LoginScreenEvents.OnLoginButtonClicked -> {
                loginUser()
            }
            is LoginScreenEvents.OnPasswordChange -> {
                _loginScreenState.update { state ->
                    state.copy(
                        password = event.value,
                    )
                }
            }
            is LoginScreenEvents.OnPasswordEyeToggle -> {
                _loginScreenState.update { state ->
                    state.copy(
                        isPasswordVisible = !loginScreenState.value.isPasswordVisible,
                    )
                }
            }
        }
    }

    private fun loginUser() {
        _loginScreenState.value.apply {
            if (email.isEmpty()) {
                sendUiEvent(UiEvents.ShowSnackBar("Email cannot be empty!!"))
                return
            }

            if (password.isEmpty()) {
                sendUiEvent(UiEvents.ShowSnackBar("Password cannot be empty!!"))
                return
            }

            _loginScreenState.update {
                it.copy(
                    isSignInLoading = true,
                )
            }

            authentication.loginUser(email, password).onEach { result ->
                when (result) {
                    is Result.Success -> {
                        sendUiEvent(UiEvents.ShowSnackBar("Log In Successful!!"))
                        sendUiEvent(UiEvents.Navigate(NavigationScreens.HomeScreen.route))
                    }
                    is Result.Error -> {
                        _loginScreenState.update {
                            it.copy(
                                isSignInLoading = false,
                            )
                        }
                        sendUiEvent(UiEvents.ShowSnackBar(result.message ?: "Sign in failed!!"))
                    }
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvents) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
}

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isSignInLoading: Boolean = false,
)

sealed interface LoginScreenEvents {
    data class OnEmailChange(val value: String) : LoginScreenEvents

    data class OnPasswordChange(val value: String) : LoginScreenEvents

    data object OnPasswordEyeToggle : LoginScreenEvents

    data object OnLoginButtonClicked : LoginScreenEvents
}
