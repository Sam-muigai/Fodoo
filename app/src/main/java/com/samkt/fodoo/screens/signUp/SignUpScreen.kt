package com.samkt.fodoo.screens.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.samkt.fodoo.R
import com.samkt.fodoo.navigation.NavigationScreens
import com.samkt.fodoo.screens.components.FodooPasswordTextField
import com.samkt.fodoo.screens.components.FodooTextField
import com.samkt.fodoo.ui.theme.FodooTheme
import com.samkt.fodoo.ui.theme.neutrif
import com.samkt.fodoo.utils.UiEvents
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignUpScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val signUpScreenState = viewModel.signUpScreenState.collectAsState().value

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(
        key1 = Unit,
        block = {
            viewModel.uiEvents.collectLatest { uiEvent: UiEvents ->
                when (uiEvent) {
                    is UiEvents.Navigate -> {
                        navController.navigate(
                            NavigationScreens.HomeScreen.route,
                        ) {
                            popUpTo(NavigationScreens.Authentication.route) {
                                inclusive = true
                            }
                        }
                    }

                    is UiEvents.ShowSnackBar -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(uiEvent.message)
                        }
                    }
                }
            }
        },
    )

    SignUpScreenContent(
        signUpScreenState = signUpScreenState,
        onEvent = viewModel::onEvent,
        snackBarHostState = snackbarHostState,
    )
}

@Composable
fun SignUpScreenContent(
    modifier: Modifier = Modifier,
    signUpScreenState: SignUpScreenState,
    onEvent: (SignUpScreenEvents) -> Unit,
    snackBarHostState: SnackbarHostState? = null,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = null,
                    )
                }
            }
        },
        snackbarHost = {
            snackBarHostState?.let {
                SnackbarHost(hostState = snackBarHostState)
            }
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(id = R.drawable.fodoo_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Fodoo",
                    style =
                        MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                        ),
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Email",
                style =
                    MaterialTheme.typography.bodySmall
                        .copy(
                            fontFamily = neutrif,
                            color = Color.Gray,
                        ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            FodooTextField(
                text = signUpScreenState.email,
                onTextChange = { value ->
                    onEvent(SignUpScreenEvents.OnEmailChange(value))
                },
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Password",
                style =
                    MaterialTheme.typography.bodySmall
                        .copy(
                            fontFamily = neutrif,
                            color = Color.Gray,
                        ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            FodooPasswordTextField(
                text = signUpScreenState.password,
                isPasswordVisible = signUpScreenState.passwordVisible,
                onTextChange = { value ->
                    onEvent(SignUpScreenEvents.OnPasswordChange(value))
                },
                onEyeClicked = {
                    onEvent(SignUpScreenEvents.OnPasswordEyeToggle)
                },
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Confirm password",
                style =
                    MaterialTheme.typography.bodySmall
                        .copy(
                            fontFamily = neutrif,
                            color = Color.Gray,
                        ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            FodooPasswordTextField(
                text = signUpScreenState.confirmPassword,
                isPasswordVisible = signUpScreenState.confirmPasswordVisible,
                onTextChange = { value ->
                    onEvent(SignUpScreenEvents.OnConfirmPasswordChange(value))
                },
                onEyeClicked = {
                    onEvent(SignUpScreenEvents.OnConfirmPasswordEyeToggle)
                },
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        onEvent(SignUpScreenEvents.OnSignUpClicked)
                    },
                    contentPadding =
                        PaddingValues(
                            start = 32.dp,
                            top = 16.dp,
                            end = 32.dp,
                            bottom = 16.dp,
                        ),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF4C4C),
                        ),
                ) {
                    Text(
                        text = "Sign Up",
                        style =
                            MaterialTheme.typography.bodySmall
                                .copy(
                                    fontWeight = FontWeight.Bold,
                                ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "or continue with",
                    style =
                        MaterialTheme.typography.bodySmall
                            .copy(
                                fontWeight = FontWeight.Bold,
                            ),
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "google",
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(24.dp))
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.messenger),
                    contentDescription = "messenger",
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(24.dp))
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = "twitter",
                    contentScale = ContentScale.Fit,
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    FodooTheme {
        SignUpScreenContent(
            signUpScreenState = SignUpScreenState(),
            onEvent = {},
        )
    }
}
