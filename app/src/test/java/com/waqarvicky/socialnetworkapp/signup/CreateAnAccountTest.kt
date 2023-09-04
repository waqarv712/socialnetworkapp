package com.waqarvicky.socialnetworkapp.signup

import com.waqarvicky.socialnetworkapp.InstantTaskExecutorExtension
import com.waqarvicky.socialnetworkapp.domain.user.User
import com.waqarvicky.socialnetworkapp.domain.user.UserRepository
import com.waqarvicky.socialnetworkapp.domain.validation.RegxCredentialsValidator
import com.waqarvicky.socialnetworkapp.signup.state.SignUpState
import com.waqarvicky.socialnetworkapp.user.InMemoryUserCatalog
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class CreateAnAccountTest {

    val viewModel = SignUpViewModel(
        RegxCredentialsValidator(),
        UserRepository(InMemoryUserCatalog())
    )

    @Test
    fun accountCreated() {
        val waqar = User("waqarId", "waqar@socianetwork.com", "about Waqar")

        viewModel.createAccount(waqar.email, "Waqar@2023", waqar.about)

        assertEquals(SignUpState.SignedUp(waqar), viewModel.signUpState.value)
    }

    @Test
    fun anotherAccountCreated() {
        val bob = User("bobId","bob@socialnetwork.com", "about Bob")

        viewModel.createAccount(bob.email, "Ple@socialnetwork1.com", bob.about)
        assertEquals(SignUpState.SignedUp(bob), viewModel.signUpState.value)
    }

    @Test
    fun createDuplicateAccount() {
        val anna = User("annaId", "anna@socialnetwork.com", "about Anna")

        val viewModel = SignUpViewModel(
            RegxCredentialsValidator(),
            UserRepository(InMemoryUserCatalog())
        ).also {
            it.createAccount(anna.email, "AnNaPas$123", anna.about)
        }

        viewModel.createAccount(anna.email, "AnNaPas$123", anna.about)

        assertEquals(SignUpState.DuplicateAccount, viewModel.signUpState.value)
    }

    
}