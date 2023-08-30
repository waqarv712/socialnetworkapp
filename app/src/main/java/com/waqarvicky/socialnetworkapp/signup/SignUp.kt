package com.waqarvicky.socialnetworkapp.signup

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waqarvicky.socialnetworkapp.R
import com.waqarvicky.socialnetworkapp.common.SpacerHeight

@OptIn(ExperimentalMaterial3Api::class)
@Preview(device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun SignUp() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScreenTitle(R.string.createAnAccount)

        SpacerHeight(16.dp)

        var email by remember {
            mutableStateOf("")
        }
        EmailField(
            value = email,
            onValueChange = {
                email = it
            })

        SpacerHeight(8.dp)

        var password by remember {
            mutableStateOf("")
        }
        PasswordField(
            password
        ) {
            password = it
        }

        SpacerHeight(16.dp)

        Button(onClick = { }) {
            Text(text = stringResource(id = R.string.signUp))
        }
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        label = {
            Text(text = stringResource(id = R.string.password))
        },
        onValueChange = {onValueChange})
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun EmailField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        label = {
            Text(text = stringResource(id = R.string.email))
        },
        onValueChange = { onValueChange })
}

@Composable
private fun ScreenTitle(@StringRes resource: Int) {
    Text(
        text = stringResource(resource),
        style = MaterialTheme.typography.headlineLarge
    )
}