package com.example.Egida.utils

import com.example.Egida.App
import com.example.Egida.R

sealed class StatesUser {
    object PasswordsDontMatch : StatesUser()
    object EmailAndPassword : StatesUser()

}

fun statesUser(stateUser: StatesUser) =
    when (stateUser) {
        is StatesUser.PasswordsDontMatch -> App.instance.resources.getString(
            R.string.passwords_dont_match
        )
        is StatesUser.EmailAndPassword -> App.instance.resources.getString(
            R.string.email_and_password
        )
    }