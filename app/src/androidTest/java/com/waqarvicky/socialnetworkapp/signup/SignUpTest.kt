package com.waqarvicky.socialnetworkapp.signup

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.waqarvicky.socialnetworkapp.MainActivity
import org.junit.Rule
import org.junit.Test

class SignUpTest {

    @get:Rule
    val signUpTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun performSignUp() {
        launchSignUpScreen(signUpTestRule) {
            typeEmail("waqarvicky@socialnetwork.app")
            typePassword("PassW0rd!")
            submit()
        } verify {
            timelineScreenIsPresent()
        }

    }

}