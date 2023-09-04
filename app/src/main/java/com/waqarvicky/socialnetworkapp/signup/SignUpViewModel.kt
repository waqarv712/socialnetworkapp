package com.waqarvicky.socialnetworkapp.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.waqarvicky.socialnetworkapp.domain.user.User
import com.waqarvicky.socialnetworkapp.domain.validation.CredentialsValidationResult
import com.waqarvicky.socialnetworkapp.domain.validation.RegxCredentialsValidator
import com.waqarvicky.socialnetworkapp.signup.state.SignUpState

class SignUpViewModel(
    private val credentialsValidator: RegxCredentialsValidator
) {
    private val _mutableSignUpState: MutableLiveData<SignUpState> = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _mutableSignUpState

    private val usersForPassword = mutableMapOf<String, MutableList<User>>()

    fun createAccount(
        email: String,
        password: String,
        about: String
    ) {

        when (credentialsValidator.validate(email, password)) {
            is CredentialsValidationResult.InvalidEmail ->
                _mutableSignUpState.value = SignUpState.BadEmail

            is CredentialsValidationResult.InvalidPassword ->
                _mutableSignUpState.value = SignUpState.BadPassword

            is CredentialsValidationResult.Valid -> {
                val isKnown = usersForPassword.values
                    .flatten()
                    .any { it.email == email }
                if (isKnown) {
                    _mutableSignUpState.value = SignUpState.DuplicateAccount
                } else {
                    val userId = email.takeWhile { it != '@' } + "Id"
                    val user = User(userId, email, about)
                    usersForPassword.getOrPut(password, ::mutableListOf).add(user)
                    _mutableSignUpState.value = SignUpState.SignedUp(user)
                }
            }
        }
    }


}
