package com.waqarvicky.socialnetworkapp.user

import com.waqarvicky.socialnetworkapp.domain.exceptions.DuplicateAccountException
import com.waqarvicky.socialnetworkapp.domain.user.User

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