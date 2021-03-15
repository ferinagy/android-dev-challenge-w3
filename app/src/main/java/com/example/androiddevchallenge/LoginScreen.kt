/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightLoginPreview() {
    MyTheme {
        LoginScreen()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkLoginPreview() {
    MyTheme(true) {
        LoginScreen()
    }
}

@Composable
fun LoginScreen(onLoginClicked: () -> Unit = {}) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        LoginBackground()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "LOG IN",
                style = MaterialTheme.typography.h1,
                modifier = Modifier.paddingFromBaseline(top = 200.dp, bottom = 32.dp)
            )

            LoginTextField("Email address")

            Spacer(Modifier.height(8.dp))

            LoginTextField(
                placeholder = "Password",
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(Modifier.height(8.dp))

            MySootheButton(text = "Log in", onClick = onLoginClicked)

            SignUpText()
        }
    }
}

@Composable
private fun LoginTextField(
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var textState by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        value = textState,
        onValueChange = { textState = it },
        placeholder = { Text(placeholder) },
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onSurface,
            backgroundColor = MaterialTheme.colors.surface
        ),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions
    )
}

@Composable
private fun SignUpText() {
    Text(
        text = buildAnnotatedString {
            append("Don't have an account? ")
            pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
            append("Sign up")
        },
        style = MaterialTheme.typography.body1,
        modifier = Modifier.paddingFromBaseline(top = 32.dp)
    )
}

@Composable
private fun LoginBackground() {
    Image(
        painter = painterResource(R.drawable.login),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}
