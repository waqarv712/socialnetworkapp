package com.waqarvicky.socialnetworkapp.signup.state

import com.waqarvicky.socialnetworkapp.domain.user.User

sealed class SignUpState {

    object BadEmail : SignUpState()
    object BadPassword : SignUpState()
    data class SignedUp(val user: User): SignUpState()

}
