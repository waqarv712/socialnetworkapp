package com.waqarvicky.socialnetworkapp.signup

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waqarvicky.socialnetworkapp.R
import com.waqarvicky.socialnetworkapp.common.SpacerHeight
import com.waqarvicky.socialnetworkapp.domain.user.UserRepository
import com.waqarvicky.socialnetworkapp.domain.validation.RegxCredentialsValidator
import com.waqarvicky.socialnetworkapp.signup.state.SignUpState
import com.waqarvicky.socialnetworkapp.user.InMemoryUserCatalog

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun SignUp(
    onsignedUp: () -> Unit
) {

    val credentialsValidator = RegxCredentialsValidator()
    val userRepository = UserRepository(InMemoryUserCatalog())
    val signUpViewModel = SignUpViewModel(credentialsValidator, userRepository)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var about by remember { mutableStateOf("") }

    val signUpState by signUpViewModel.signUpState.observeAsState()

    if (signUpState is SignUpState.SignedUp) {
        onsignedUp()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScreenTitle(R.string.createAnAccount)

        SpacerHeight(16.dp)

        EmailField(
            value = email,
            onValueChange = {
                email = it
            })

        SpacerHeight(8.dp)

        PasswordField(
            password
        ) {
            password = it
        }
        SpacerHeight(8.dp)

        AboutField(
            value = about,
            onValueChange = {about = it}
        )

        SpacerHeight(16.dp)

        Button(onClick = {
            signUpViewModel.createAccount(email, password, about)
        }) {
            Text(text = stringResource(id = R.string.signUp))
        }
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun EmailField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        label = {
            Text(text = stringResource(id = R.string.email))
        },
        onValueChange = onValueChange
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val visualTransformation =
        if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        label = {
            Text(text = stringResource(id = R.string.password))
        },
        visualTransformation = visualTransformation,
        trailingIcon = {
            VisibilityToggle(passwordVisible)
        },
        onValueChange = onValueChange
    )
}

@Composable
private fun VisibilityToggle(passwordVisible: Boolean) {
    var passwordVisible1 = passwordVisible
    val image = if (passwordVisible1)
        Icons.Filled.Visibility
    else Icons.Filled.VisibilityOff
    // Please provide localized description for accessibility services
    val description = if (passwordVisible1) "Hide password" else "Show password"

    IconButton(onClick = { passwordVisible1 = !passwordVisible1 }) {
        Icon(imageVector = image, description)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        label = {
            Text(text = stringResource(id = R.string.about))
        },
        onValueChange = onValueChange
    )
}

@Composable
private fun ScreenTitle(@StringRes resource: Int) {
    Text(
        text = stringResource(resource),
        style = MaterialTheme.typography.headlineLarge
    )
}