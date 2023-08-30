package com.waqarvicky.socialnetworkapp.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.waqarvicky.socialnetworkapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun SignUp() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.create_an_account))
        OutlinedTextField(value = "",
            label = {
                Text(text = stringResource(id = R.string.email))
            },
            onValueChange = {})
        OutlinedTextField(value = "",
            label = {
                Text(text = stringResource(id = R.string.password))
            },
            onValueChange = {})

        Button(onClick = { }) {
            Text(text = stringResource(id = R.string.signUp))
        }
    }

}