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
                _mutableSignUpState.value = signUp(email, about, password)
            }
        }
    }

    val userCatalog = InMemoryUserCatalog()

    private fun signUp(
        email: String,
        about: String,
        password: String
    ): SignUpState {
        val result = try {
            val user = userCatalog.createUser(email, about, password)
            SignUpState.SignedUp(user)
        } catch (duplicateAccount: DuplicateAccountException) {
            SignUpState.DuplicateAccount
        }
        return result
    }

    class InMemoryUserCatalog(
        private val usersForPassword: MutableMap<String, MutableList<User>> = mutableMapOf()
    ) {
        fun createUser(
            email: String,
            about: String,
            password: String
        ): User {
            checkAccountExists(email)
            val userId = createUserIdForEmail(email)
            val user = User(userId, email, about)
            saveUser(password, user)
            return user
        }

        private fun saveUser(
            password: String,
            user: User
        ) {
            usersForPassword.getOrPut(password, ::mutableListOf).add(user)
        }

        private fun createUserIdForEmail(email: String) = email.takeWhile { it != '@' } + "Id"

        private fun checkAccountExists(email: String) {
            if (usersForPassword.values.flatten().any { it.email == email }) {
                throw DuplicateAccountException()
            }
        }
    }

    class DuplicateAccountException : Throwable()


}
