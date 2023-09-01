package com.waqarvicky.socialnetworkapp.signup

import com.waqarvicky.socialnetworkapp.InstantTaskExecutorExtension
import com.waqarvicky.socialnetworkapp.signup.state.SignUpState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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


    @ParameterizedTest
    @CsvSource(
        "''",
        "'       '",
        "'12345678'",
        "'abcd5678'",
        "'abcDEF78'",
        "'abcdef78#@'",
        "'ABCDEF78#@'"
    )
    fun invalidPassword(password: String) {
        val viewModel = SignUpViewModel()
        viewModel.createAccount("waqarv712@gmail.com", password, ":about:")
        assertEquals(SignUpState.BadPassword, viewModel.signUpState.value)
    }


}