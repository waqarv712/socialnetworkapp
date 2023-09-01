package com.waqarvicky.socialnetworkapp.signup.state

sealed class SignUpState {

    object BadEmail : SignUpState()
    object BadPassword : SignUpState()

}
