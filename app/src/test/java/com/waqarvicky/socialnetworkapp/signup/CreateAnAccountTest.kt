package com.waqarvicky.socialnetworkapp.signup

import com.waqarvicky.socialnetworkapp.InstantTaskExecutorExtension
import com.waqarvicky.socialnetworkapp.domain.user.User
import com.waqarvicky.socialnetworkapp.domain.validation.RegxCredentialsValidator
import com.waqarvicky.socialnetworkapp.signup.state.SignUpState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class CreateAnAccountTest {

    @Test
    fun accountCreated() {
        val waqar = User("waqarId", "waqar@socianetwork.com", "about Waqar")
        val viewModel = SignUpViewModel(RegxCredentialsValidator())

        viewModel.createAccount(waqar.email, "Waqar@2023", waqar.about)

        assertEquals(SignUpState.SignedUp(waqar), viewModel.signUpState.value)
    }

}