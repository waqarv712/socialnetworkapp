package com.waqarvicky.socialnetworkapp.domain.validation

sealed class CredentialsValidationResult {
    object InvalidEmail : CredentialsValidationResult()
    object InvalidPassword : CredentialsValidationResult()
    object Valid : CredentialsValidationResult()

}