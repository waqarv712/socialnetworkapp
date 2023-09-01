package com.waqarvicky.socialnetworkapp.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.waqarvicky.socialnetworkapp.signup.state.SignUpState
import java.util.regex.Pattern

class SignUpViewModel {
    private val _mutableSignUpState: MutableLiveData<SignUpState> = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _mutableSignUpState

    fun createAccount(
        email: String,
        password: String,
        about: String
    ) {
        val emailRegx = """[a-zA-Z0-9+._%\-]{1,64}@[a-zA-Z0-9][a-zA-Z0-9\-]{1,64}(\.[a-zA-Z0-9][a-zA-Z0-9\-]{1,25})"""
        val emailPattern = Pattern.compile(emailRegx)
        if (!emailPattern.matcher(email).matches()){
            _mutableSignUpState.value = SignUpState.BadEmail
        }else if (password.isEmpty()){
            _mutableSignUpState.value = SignUpState.BadPassword
        }
    }


}
