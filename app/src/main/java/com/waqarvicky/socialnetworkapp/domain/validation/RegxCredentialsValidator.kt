package com.waqarvicky.socialnetworkapp.domain.validation

import java.util.regex.Pattern

class RegxCredentialsValidator {
    private companion object {
        private const val EMAIL_REGX =
            """[a-zA-Z0-9+._%\-]{1,64}@[a-zA-Z0-9][a-zA-Z0-9\-]{1,64}(\.[a-zA-Z0-9][a-zA-Z0-9\-]{1,25})"""
        private val PASSWORD_REGX =
            """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=\-]).{8,}$"""
    }

    private val emailPattern = Pattern.compile(EMAIL_REGX)
    private val passwordPattern = Pattern.compile(PASSWORD_REGX)

    fun validate(
        email: String,
        password: String
    ): CredentialsValidationResult {
        return if (!emailPattern.matcher(email).matches()) {
            CredentialsValidationResult.InvalidEmail
        } else if (!passwordPattern.matcher(password).matches()) {
            CredentialsValidationResult.InvalidPassword
        } else {
            CredentialsValidationResult.Valid
        }
    }
}