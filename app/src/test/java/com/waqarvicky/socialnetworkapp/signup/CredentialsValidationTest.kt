package com.waqarvicky.socialnetworkapp.signup

import com.waqarvicky.socialnetworkapp.InstantTaskExecutorExtension
import com.waqarvicky.socialnetworkapp.signup.state.SignUpState
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.NullSource
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(InstantTaskExecutorExtension::class)
class CredentialsValidationTest {

    @ParameterizedTest
    /*@EmptySource
    @NullSource
    @NullAndEmptySource
    @ValueSource(
        strings = [
            "email",
            "a@b.c",
            "ab@b.c",
            "ab@bc.c",
            "",
            "      "
        ]
    )*/
    @CsvSource(
        "'email'",
        "'a@b.c'",
        "'ab@b.c'",
        "'ab@bc.c'",
        "''",
        "'      '",
    )
    fun invalidEmail(email: String) {

        val viewModel = SignUpViewModel()
        viewModel.createAccount(email, ":password:", ":about:")

        assertEquals(SignUpState.BadEmail, viewModel.signUpState.value)
    }

}