package com.waqarvicky.socialnetworkapp.domain.user

import com.waqarvicky.socialnetworkapp.domain.exceptions.DuplicateAccountException
import com.waqarvicky.socialnetworkapp.signup.state.SignUpState
import com.waqarvicky.socialnetworkapp.user.InMemoryUserCatalog

class UserRepository(private val userCatalog: InMemoryUserCatalog) {
    fun signUp(
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
}