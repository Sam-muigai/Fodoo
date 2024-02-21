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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.samkt.fodoo.R
import com.samkt.fodoo.screens.components.FodooPasswordTextField
import com.samkt.fodoo.screens.components.FodooTextField
import com.samkt.fodoo.ui.theme.FodooTheme
import com.samkt.fodoo.ui.theme.neutrif

@Composable
fun SignUpScreen(navController: NavHostController) {
    SignUpScreenContent()
}

@Composable
fun SignUpScreenContent(modifier: Modifier = Modifier) {
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
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier.size(150.dp),
                    painter = painterResource(id = R.drawable.fodoo_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Name",
                style =
                    MaterialTheme.typography.bodySmall
                        .copy(
                            fontFamily = neutrif,
                            color = Color.Gray,
                        ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            FodooTextField(onTextChange = {})
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
            FodooPasswordTextField(onTextChange = {})
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
            FodooPasswordTextField(onTextChange = {})
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
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
        SignUpScreenContent()
    }
}
