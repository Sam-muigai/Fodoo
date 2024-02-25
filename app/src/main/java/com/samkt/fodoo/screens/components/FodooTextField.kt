package com.samkt.fodoo.screens.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samkt.fodoo.R
import com.samkt.fodoo.ui.theme.FodooTheme

@Composable
fun FodooTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    onTextChange: (String) -> Unit,
    @DrawableRes trailingIcon: Int = R.drawable.profile,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation =
            CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp,
            ),
        shape = RoundedCornerShape(2.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
            ),
    ) {
        BasicTextField(
            modifier = modifier.fillMaxWidth(),
            value = text,
            onValueChange = onTextChange,
            textStyle = MaterialTheme.typography.bodyLarge,
        ) { innerTextField ->
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 16.dp),
            ) {
                innerTextField()
                Icon(
                    modifier =
                        Modifier
                            .size(24.dp)
                            .align(Alignment.CenterEnd),
                    painter = painterResource(id = trailingIcon),
                    contentDescription = null,
                    tint = Color.Gray,
                )
            }
        }
    }
}

@Composable
fun FodooNumberTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    onTextChange: (String) -> Unit,
    leadingIcon: @Composable () -> Unit,
    @DrawableRes trailingIcon: Int = R.drawable.profile,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation =
            CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp,
            ),
        shape = RoundedCornerShape(2.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
            ),
    ) {
        BasicTextField(
            modifier = modifier.fillMaxWidth(),
            value = text,
            onValueChange = onTextChange,
            textStyle = MaterialTheme.typography.bodyLarge,
        ) { innerTextField ->
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 16.dp),
            ) {
                Row {
                    Box(modifier = Modifier.width(32.dp)) {
                        leadingIcon()
                    }
                    innerTextField()
                }
                Icon(
                    modifier =
                        Modifier
                            .size(24.dp)
                            .align(Alignment.CenterEnd),
                    painter = painterResource(id = trailingIcon),
                    contentDescription = null,
                    tint = Color.Gray,
                )
            }
        }
    }
}

@Composable
fun FodooPasswordTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    onTextChange: (String) -> Unit,
    isPasswordVisible: Boolean = false,
    onEyeClicked: () -> Unit = {},
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation =
            CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp,
            ),
        shape = RoundedCornerShape(2.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
            ),
    ) {
        BasicTextField(
            modifier = modifier.fillMaxWidth(),
            value = text,
            onValueChange = onTextChange,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            textStyle = MaterialTheme.typography.bodyLarge,
        ) { innerTextField ->
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 16.dp),
            ) {
                innerTextField()
                IconButton(
                    modifier =
                        Modifier
                            .size(24.dp)
                            .align(Alignment.CenterEnd),
                    onClick = onEyeClicked,
                ) {
                    Icon(
                        modifier =
                        Modifier,
                        painter =
                            painterResource(
                                id =
                                    if (isPasswordVisible) {
                                        R.drawable.eye_close
                                    } else {
                                        R.drawable.eye
                                    },
                            ),
                        contentDescription = null,
                        tint = Color.Gray,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FodooTextFieldPreview() {
    FodooTheme {
        FodooTextField(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp,
                    ),
            onTextChange = {},
        )
    }
}
